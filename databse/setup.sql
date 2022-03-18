####################
# Structure

# Tables:
# role
# user
# class
# class_enrolled
# roll_call
# attendance_record

# Triggers:
# update_class_size: When a student enroll a class, then update the class size
# update_class_total_attendance_times: When a roll call start, then update the total attendance times of the class
# update_attendance_record: When a roll call start, then put all the students(no professor) with uncheck status into the attendance record
# update_attendance_rate: When a record changed, then update the attendance rate of the roll call and student, the records are not allowed to change after the expired time

####################
# Tables
drop database if exists attendance;
create database attendance default character set utf8mb4 collate utf8mb4_unicode_ci;
use attendance;


# table roll
create table role
(
    id   int auto_increment
        primary key,
    type varchar(64) not null
);

# table user
create table user
(
    id       int auto_increment
        primary key,
    name     varchar(255) not null,
    password varchar(255) not null,
    email    varchar(128) not null,
    role_id  int          not null,

    foreign key (role_id) references role (id)
);

# table class that a professor can create a new item
create table class
(
    id               int auto_increment
        primary key,
    number           varchar(255) not null, # CS6620
    title            varchar(255) not null, # Cloud Computing
    start_date       date,
    end_date         date,
    size             int default 0,        # the number of students in this course (no professor)
    attendance_times int default 0          # total attendance times for this course
);

# table class_enrolled that bind the user with class
# good to obtain the attendance rate of a student
create table class_enrolled
(
    class_id         int,
    user_id          int,
    attendance_times int          default 0, # the attendance times in this course for this student
    attendance_rate  varchar(255) default 0, # attendance rate for this student

    primary key (class_id, user_id),
    foreign key (user_id) references user (id),
    foreign key (class_id) references class (id)

);

# table roll_call for professor to start a roll call
create table roll_call
(
    id               int auto_increment
        primary key,
    class_id         int not null ,
    expired_times    datetime,
    attendance_count int default 0,         # the count of the students who has already checked in
    attendance_rate  varchar(255) default 0, # attendance rate for this roll call
    foreign key (class_id) references class (id)
);

# when roll_call create an item then all students will be put into this table
# need to update the check_status and check_time when students check in
# table attendance_record to store all the record
create table attendance_record
(
    roll_call_id int,
    user_id      int,
    check_status bool     default false,
    check_time   datetime default null,

    primary key (roll_call_id, user_id), # the user id plus the roll call id is enough to be primary key
    foreign key (roll_call_id) references roll_call (id),
    foreign key (user_id) references user (id)
);

####################
# Triggers

# When a student enroll a class, then update the class size
Delimiter $
create trigger update_class_size
    after insert
    on class_enrolled
    for each row
begin
    if (select role_id
        from user
        where user.id = NEW.user_id) = (select id
                                        from role
                                        where role.type = 'student')
    then
        update class
        set size = size + 1
        where class.id = NEW.class_id;
    end if;
end $
Delimiter ;


# When a roll call start, then update the total attendance times of the class
create trigger update_class_total_attendance_times
    after insert
    on roll_call
    for each row
    update class
    set class.attendance_times = class.attendance_times + 1
    where class.id = NEW.class_id;

# When a roll call start, then put all the students(no professor) with uncheck status into the attendance record
create trigger update_attendance_record
    after insert
    on roll_call
    for each row
    # select all the students enrolled this class by class_id
    # put all the students into the record
    insert into attendance_record (roll_call_id, user_id)
    select NEW.id as roll_call_id, user_id
    from class_enrolled
    where class_enrolled.class_id = NEW.class_id
      and (select role_id
           from user
           where user.id = class_enrolled.user_id) = (select id
                                                      from role
                                                      where role.type = 'student');

# When a record changed, then update the attendance rate of the roll call and student
# the records are not allowed to change after the expired time
Delimiter $
create trigger update_attendance_rate
    after update
    on attendance_record
    for each row
begin
    # check
    if(NEW.check_status = true and OLD.check_status = false) then
    # update the student attendance count
    update class_enrolled
    set attendance_times = attendance_times + 1
    where class_enrolled.class_id = (select class_id from roll_call where roll_call.id = NEW.roll_call_id)
      and class_enrolled.user_id = NEW.user_id;
    # update the roll call attendance count
    update roll_call set attendance_count = attendance_count + 1 where roll_call.id = NEW.roll_call_id;
    end if;

    # cancel check
    if(NEW.check_status = false and OLD.check_status = true) then
    # update the student attendance count
    update class_enrolled
    set attendance_times = attendance_times - 1
    where class_enrolled.class_id = (select class_id from roll_call where roll_call.id = NEW.roll_call_id)
      and class_enrolled.user_id = NEW.user_id;
    # update the roll call attendance count
    update roll_call set attendance_count = attendance_count - 1 where roll_call.id = NEW.roll_call_id;
    end if;

    # update the student attendance rate
    update class_enrolled
    set attendance_rate = class_enrolled.attendance_times /
                          (select attendance_times from class where class.id = class_enrolled.class_id)
    where class_enrolled.class_id = (select class_id from roll_call where id = NEW.roll_call_id)
      and class_enrolled.user_id = NEW.user_id;
    # update the roll call attendance rate
    update roll_call
    set attendance_rate = roll_call.attendance_count /
                          (select size from class where class.id = roll_call.class_id)
    where roll_call.id = NEW.roll_call_id;
end $
Delimiter ;

## Test

# init the table role
insert into role(type)
values ('professor');
insert into role(type)
values ('student');

# init the user
insert into user(name, password, email, role_id)
values ('shutong', '1234', 'shutong@gmail.com', 1);
insert into user(name, password, email, role_id)
values ('jaying', '1234', 'jaying@gmail.com', 2);
insert into user(name, password, email, role_id)
values ('yanwang', '1234', 'yanwang@gmail.com', 2);
insert into user(name, password, email, role_id)
values ('tianyu', '1234', 'tinayu@gmail.com', 2);
insert into user(name, password, email, role_id)
values ('wayson', '1234', 'wayson@gmail.com', 2);

# init the class
insert into class(number, title)
values ('CS6620', 'Cloud Computing');
insert into class(number, title)
values ('CS5800', 'Algorithm');
insert into class(number, title)
values ('CS5520', 'Android Development');

# init the class_enrolled table, then a trigger to update the class size
insert into class_enrolled(class_id, user_id)
values (1, 1);
insert into class_enrolled(class_id, user_id)
values (1, 2);
insert into class_enrolled(class_id, user_id)
values (2, 1);
insert into class_enrolled(class_id, user_id)
values (3, 1);
insert into class_enrolled(class_id, user_id)
values (3, 2);
insert into class_enrolled(class_id, user_id)
values (3, 3);
insert into class_enrolled(class_id, user_id)
values (3, 4);

# init roll call then a trigger to update the total attendance number of the class and put all students who enrolled this class into the record table
insert into roll_call(class_id) values (1);
insert into roll_call(class_id) values (2);
insert into roll_call(class_id) values (3);
insert into roll_call(class_id) values (3);

# init record table then test the trigger attendance rate
update attendance_record set check_status = true where roll_call_id = 4 and user_id = 4;
update attendance_record set check_status = true where roll_call_id = 1 and user_id = 2;
update attendance_record set check_status = false where roll_call_id = 1 and user_id = 2; # test cancel check

# Will delete next time
create table `test`
(
    `id` int,
    `name` varchar(255),
    `age` int
);
INSERT INTO test VALUES(1, 'yzy', 18);
INSERT INTO test VALUES(2, 'wy', 12);
INSERT INTO test VALUES(3, 'wayson', 17);
INSERT INTO test VALUES(3, 'shutong', 12);
INSERT INTO test VALUES(3, 'tianyu', 16);