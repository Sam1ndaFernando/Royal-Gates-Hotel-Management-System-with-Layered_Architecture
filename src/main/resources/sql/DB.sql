create table customer
(
    customerId    varchar(10)  not null
        primary key,
    customer_name varchar(75)  null,
    date          date         null,
    nic           varchar(15)  null,
    address       varchar(150) null,
    Mobile        varchar(12)  null,
    Email         varchar(100) null,
    Province      varchar(50)  null
);

create table employee
(
    employeeId varchar(10)  not null
        primary key,
    name       varchar(100) null,
    address    varchar(150) null,
    join_date  date         null,
    nic        varchar(12)  null,
    Email      varchar(100) null,
    mobile     varchar(18)  null,
    jobRole    varchar(100) null
);

create table hall_availability
(
    hall_number varchar(10) not null
        primary key,
    date        date        null,
    time        time        null,
    status      varchar(10) null
);

create table hallreservationdetail
(
    hallNumber     varchar(10) null,
    customer_id    varchar(10) null,
    reservationId  varchar(10) not null
        primary key,
    check_out_date date        null,
    check_in_date  date        null,
    constraint hallreservationdetail_ibfk_1
        foreign key (hallNumber) references hall_availability (hall_number)
            on update cascade on delete cascade,
    constraint hallreservationdetail_ibfk_2
        foreign key (customer_id) references customer (customerId)
            on update cascade on delete cascade
);

create index customer_id
    on hallreservationdetail (customer_id);

create index hall_number
    on hallreservationdetail (hallNumber);

create table halls
(
    hallNumber varchar(10) not null
        primary key,
    hall_type  varchar(10) not null,
    status     varchar(10) null,
    price      double      null
);

create table hallmaintenance
(
    maintenanceId varchar(10) not null
        primary key,
    hall_number   varchar(10) null,
    date          date        null,
    start_time    time        null,
    end_time      time        null,
    constraint hallmaintenance_ibfk_1
        foreign key (hall_number) references halls (hallNumber)
            on update cascade on delete cascade
);

create index hall_number
    on hallmaintenance (hall_number);

create table mealpackages
(
    pkg_id      varchar(10)  not null
        primary key,
    price       double       null,
    description varchar(200) null,
    meal_plan   varchar(20)  null,
    type        varchar(45)  null
);

create table meal_oders
(
    oder_id     varchar(10) not null
        primary key,
    customer_id varchar(10) null,
    date        date        null,
    qty         int         null,
    pkg_id      varchar(10) null,
    constraint meal_oders_ibfk_1
        foreign key (pkg_id) references mealpackages (pkg_id)
            on update cascade on delete cascade
);

create index pkg_id
    on meal_oders (pkg_id);

create table room
(
    roomNumber varchar(10) not null
        primary key,
    room_type  varchar(80) null,
    status     varchar(10) null,
    price      double      null
);

create table complain
(
    room_number varchar(10)  null,
    hall_number varchar(10)  null,
    complaintId varchar(10)  not null
        primary key,
    customer_id varchar(10)  null,
    date        date         null,
    time        time         null,
    description varchar(200) null,
    constraint complain_ibfk_1
        foreign key (hall_number) references halls (hallNumber)
            on update cascade on delete cascade,
    constraint complain_ibfk_2
        foreign key (room_number) references room (roomNumber)
            on update cascade on delete cascade,
    constraint complain_ibfk_3
        foreign key (customer_id) references customer (customerId)
            on update cascade on delete cascade
);

create index customer_id
    on complain (customer_id);

create index hall_number
    on complain (hall_number);

create index room_number
    on complain (room_number);

create table room_availability
(
    room_id varchar(10) not null
        primary key,
    date    date        null,
    time    time        null,
    status  varchar(10) null
);

create table roommaintenance
(
    maintenanceId varchar(10) not null
        primary key,
    room_number   varchar(10) null,
    date          date        null,
    start_time    time        null,
    end_time      time        null,
    constraint roommaintenance_ibfk_1
        foreign key (room_number) references room (roomNumber)
            on update cascade on delete cascade
);

create index room_number
    on roommaintenance (room_number);

create table roomreservationdetail
(
    room_number    varchar(10) null,
    customer_id    varchar(10) null,
    reservation_id varchar(10) not null
        primary key,
    check_out_date date        null,
    check_in_date  date        null,
    constraint roomreservationdetail_ibfk_1
        foreign key (room_number) references room (roomNumber)
            on update cascade on delete cascade,
    constraint roomreservationdetail_ibfk_2
        foreign key (customer_id) references customer (customerId)
            on update cascade on delete cascade
);

create table payment
(
    paymentId      varchar(10) not null
        primary key,
    reservation_id varchar(10) null,
    time           time        null,
    date           date        null,
    oder_id        varchar(10) null,
    customer_id    varchar(10) null,
    amount         double      null,
    constraint payment_ibfk_1
        foreign key (customer_id) references customer (customerId)
            on update cascade on delete cascade,
    constraint payment_ibfk_2
        foreign key (reservation_id) references roomreservationdetail (reservation_id)
            on update cascade on delete cascade
);

create index customer_id
    on payment (customer_id);

create index reservation_id
    on payment (reservation_id);

create index customer_id
    on roomreservationdetail (customer_id);

create index room_number
    on roomreservationdetail (room_number);

create table user
(
    Employee_id varchar(10)  not null
        primary key,
    name        varchar(100) null,
    job_role    varchar(50)  null,
    username    varchar(40)  null,
    password    varchar(20)  null,
    constraint user_ibfk_1
        foreign key (Employee_id) references employee (employeeId)
            on update cascade on delete cascade
);

