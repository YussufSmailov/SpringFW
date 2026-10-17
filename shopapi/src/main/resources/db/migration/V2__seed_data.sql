INSERT INTO customers(name,email) VALUES
                             ('Yussuf Smailov', 'y_smailov@kbtu.kz'),
                             ('Ivan Ivanov', 'i_ivanov@kbtu.kz'),
                             ('Jaylet Oliver', 'j_olivier@kbtu.kz');

INSERT INTO orders (product, quantity, price, customer_id) VALUES
      ('Laptop',   1, 450000, (SELECT id FROM customers WHERE email = 'y_smailov@kbtu.kz')),
      ('Mouse',    2,   8000, (SELECT id FROM customers WHERE email = 'y_smailov@kbtu.kz')),
      ('Monitor',  1, 120000, (SELECT id FROM customers WHERE email = 'i_ivanov@kbtu.kz')),
      ('Keyboard', 1,  25000, (SELECT id FROM customers WHERE email = 'i_ivanov@kbtu.kz')),
      ('Headset',  1,  35000, (SELECT id FROM customers WHERE email = 'j_olivier@kbtu.kz'));
