--Вывести к каждому самолету класс обслуживания и количество мест этого класса
select aircrafts.model, seats.fare_conditions, count(seats.seat_no) from aircrafts
join seats on aircrafts.aircraft_code = seats.aircraft_code
group by aircrafts.model, seats.fare_conditions
order by aircrafts.model, seats.fare_conditions;


--Найти 3 самых вместительных самолета (модель + кол-во мест)
select aircrafts.model, count(seats.seat_no) from aircrafts
join seats on aircrafts.aircraft_code = seats.aircraft_code
group by aircrafts.model
order by count(seats.seat_no) desc
limit 3;


--Вывести код,модель самолета и места не эконом класса для самолета 'Аэробус A321-200' с сортировкой по местам
select aircrafts.aircraft_code, aircrafts.model, seats.seat_no from aircrafts
join seats on aircrafts.aircraft_code = seats.aircraft_code
    and aircrafts.model like 'Аэробус A321-200'
    and seats.fare_conditions not like 'Economy'
group by aircrafts.aircraft_code, aircrafts.model, seats.seat_no
order by seats.seat_no;


--Вывести города в которых больше 1 аэропорта ( код аэропорта, аэропорт, город)
select airport_code, airport_name, city from airports
where city in (
    select city from airports
    group by city
    having count(city) > 1
);


-- Найти ближайший вылетающий рейс из Екатеринбурга в Москву, на который еще не завершилась регистрация
select departure_airport, arrival_airport, departure.city, arrival.city, scheduled_departure from flights
join airports as departure on (departure.airport_code = departure_airport and departure.city='Екатеринбург')
join airports as arrival on (arrival.airport_code = arrival_airport and arrival.city='Москва')
where flights.status like 'Arrived'
order by flights.scheduled_departure
limit 1;


--Вывести самый дешевый и дорогой билет и стоимость ( в одном результирующем ответе)
(select * from ticket_flights
order by amount desc
limit 1)
UNION
(select * from ticket_flights
order by amount
limit 1)


-- Написать DDL таблицы Customers , должны быть поля id , firstName, LastName, email , phone. Добавить ограничения на поля ( constraints) .
CREATE TABLE customers_data (
    customer_id integer NOT NULL,
    firstName jsonb NOT NULL,
    LastName jsonb NOT NULL,
    email character(35),
    phone character(15) NOT NULL
);
-- customers_pkey
ALTER TABLE ONLY customers_data
    ADD CONSTRAINT customers_pkey PRIMARY KEY (customer_id);
-- customers_data_customer_id_seq
CREATE SEQUENCE customers_data_customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE;
ALTER SEQUENCE customers_data_customer_id_seq OWNED BY customers_data.customer_id;
ALTER TABLE ONLY customers_data ALTER COLUMN customer_id SET DEFAULT nextval('customers_data_customer_id_seq'::regclass);


-- Написать DDL таблицы Orders , должен быть id, customerId, quantity.
CREATE TABLE orders_data (
    order_id integer NOT NULL,
    customer_id integer NOT NULL,
    quantity int default 0,
    CONSTRAINT orders_quantity_check CHECK (quantity >= 0)
);
--orders_pkey
ALTER TABLE ONLY orders_data
    ADD CONSTRAINT orders_pkey PRIMARY KEY (order_id);
--orders_data_order_id_seq
CREATE SEQUENCE orders_data_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE;
ALTER SEQUENCE orders_data_order_id_seq OWNED BY orders_data.order_id;
ALTER TABLE ONLY orders_data ALTER COLUMN order_id SET DEFAULT nextval('orders_data_order_id_seq'::regclass);


-- Должен быть внешний ключ на таблицу customers + ограничения
ALTER TABLE ONLY orders_data
    ADD CONSTRAINT customers_orders_code_fkey FOREIGN KEY (customer_id) REFERENCES customers_data(customer_id);

-- Написать 5 insert в эти таблицы
INSERT INTO customers_data (customer_id, firstName, LastName, phone, email) VALUES
    (1,	'{"en": "Ivan", "ru": "Иван"}',	'{"en": "Ivanov", "ru": "Иванов"}', '+375250009876', 'ivanovw3@gmail.com'),
    (2,	'{"en": "Ivan", "ru": "Иван"}',	'{"en": "Abramov", "ru": "Абрамов"}', '+375250009876', 'ivanAbramov@gmail.com'),
    (3,	'{"en": "Oleg", "ru": "Олег"}',	'{"en": "Ivan", "ru": "Иван"}', '375250009876', 'OleleGo23fifa@gmail.com'),
    (4,	'{"en": "Kity", "ru": "Катя"}',	'{"en": "Kleno", "ru": "Клено"}', '+375250009876', 'Keityty@gmail.com'),
    (5,	'{"en": "Arthur", "ru": "Artur"}',	'{"en": "Kirilenko", "ru": "Кириленко"}', '375250009876', 'Arth13tr@gmail.com');

INSERT INTO orders_data (order_id, customer_id, quantity)VALUES
    (1,	1,	20),
    (2,	5,	3),
    (3,	2,	19),
    (4,	4,	3),
    (5,	2,	1);


-- Получить список email для пользователей, которые сделали не менее 20 покупок
select customers_data.email from customers_data
where customers_data.customer_id in (
    select o.customer_id from orders_data o
    join customers_data c on c.customer_id = o.order_id
    group by o.customer_id
    having sum(o.quantity) >= 20
);


-- удалить таблицы
DROP TABLE IF EXISTS orders_data;
DROP TABLE IF EXISTS customers_data;
