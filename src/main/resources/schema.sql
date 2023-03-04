DROP table IF EXISTS products;
DROP table IF EXISTS discount_cards;

CREATE TABLE products (
                          id bigserial PRIMARY KEY NOT NULL,
                          name varchar(30) UNIQUE NOT NULL,
                          price double precision NOT NULL,
                          discount_percentage smallint NOT NULL
);
CREATE TABLE discount_cards (
                                id bigserial PRIMARY KEY NOT NULL,
                                fio varchar(30) ,
                                number_card int NOT NULL
);
