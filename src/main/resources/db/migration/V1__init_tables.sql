-- INSTRUMENT_TYPE ----------------------------------------
drop table if exists insrtrument_type;
create table instrument_type
(
    id        serial primary key,
    code      varchar(256) not null,
    name      varchar(255)
);
create unique index instrument_type_code_uindex
    on instrument_type(code);
-----------------------------------------------------------

-- INSTRUMENT ---------------------------------------------
drop table if exists instrument cascade;
create table instrument
(
    id                 serial primary key,
    figi               varchar(255) not null,
    isin               varchar(255),
    ticker             varchar(255) not null,
    currency           varchar(255) not null,
    increment          numeric(19, 4),
    name               varchar(255),
    CHECK (increment >= 0),
    lot                integer      not null,
    instrument_type_id varchar(255) not null,
    foreign key (instrument_type_id) references instrument_type (id)
);
create unique index instrument_figi_uindex
    on instrument (figi);
create index instrument_ticker_index
    on instrument (ticker);
create index instrument_isin_index
    on instrument(isin);
create index instrument_currency_index
    on instrument (currency);
create index instrument_type_id_index
    on instrument (instrument_type_id);
-----------------------------------------------------------

-- TIMEFRAME -------------------------------------------------
drop table if exists timeframe cascade;
create table timeframe
(
    id   serial primary key,
    code varchar(64) not null,
    name varchar(256)
);
create unique index timeframe_code_uindex
    on timeframe(code);
-----------------------------------------------------------

-- CANDLESTICK --------------------------------------------
drop table if exists candlestick;
create table candlestick
(
    id            serial primary key,
    maximum_value numeric(20, 10),
    CHECK (maximum_value >= minimum_value),
    minimum_value numeric(20, 10),
    opened_value  numeric(20, 10),
    closed_value  numeric(20, 10),
    volume        integer CHECK (volume >= 0),
    since         timestamp not null,
    timeframe_id  integer,
    instrument_id integer,
    foreign key (timeframe_id) references timeframe (id),
    foreign key (instrument_id) references instrument (id)
);
create unique index candlestick_ticker_timeframe_since
    on candlestick (instrument_id, timeframe_id, since);
-----------------------------------------------------------

-- DIVIDEND -----------------------------------------------
drop table if exists dividend cascade;
create table dividend
(
    id            serial primary key,
    instrument_id int not null,
    since         timestamp,
    close_date    timestamp,
    type          varchar(8) not null,
    value         numeric(20, 10),
    CHECK (value > 0),
    foreign key (instrument_id) references instrument (id)
);
create unique index dividend_instrument_id_since_uindex
    on dividend (instrument_id, since);
-----------------------------------------------------------

