INSERT INTO INSTRUMENT_TYPE (id, code, name)
VALUES (1, 'currency', 'валюта'),
       (2, 'stock', 'акция'),
       (3, 'bond', 'облигация'),
       (4, 'etf', 'биржевой фонд');

INSERT INTO TIMEFRAME (id, code, name)
VALUES (1,  'MIN1', '1 минута'),
       (2,  'MIN2', '2 минуты'),
       (3,  'MIN5', '5 минут'),
       (4,  'MIN10', '10 минут'),
       (5,  'MIN15', '15 минут'),
       (6,  'MIN30', '30 минут'),
       (7,  'HOUR1', '1 час'),
       (8,  'DAY1', 'день'),
       (9,  'WEEK1', 'неделя'),
       (10, 'MON1', 'месяц');
