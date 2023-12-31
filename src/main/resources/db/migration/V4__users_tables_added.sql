CREATE TABLE security_role
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    description varchar(100) DEFAULT NULL,
    role_name   varchar(100) DEFAULT NULL
);


CREATE TABLE security_user
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    username   varchar(255) NOT NULL,
    password   varchar(255) NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NOT NULL
);


CREATE TABLE user_role
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    CONSTRAINT FK_SECURITY_USER_ID FOREIGN KEY (user_id) REFERENCES security_user (id),
    CONSTRAINT FK_SECURITY_ROLE_ID FOREIGN KEY (role_id) REFERENCES security_role (id)
);

-- USER
-- hashed password: letmein
INSERT INTO security_user (id, username, password, first_name, last_name) VALUES
                                                                              (1,  'admin', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu', 'Administrator', 'Adminstrator'),
                                                                              (2,  'csr_jane', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu', 'Jane', 'Doe'),
                                                                              (3,  'csr_mark', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu', 'Mark', 'Smith'),
                                                                              (4,  'wally', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu', 'Walter', 'Adams');

-- ROLES

INSERT INTO security_role (id, role_name, description) VALUES (1, 'ROLE_ADMIN', 'Administrator');
INSERT INTO security_role (id, role_name, description) VALUES (2, 'ROLE_CSR', 'Customer Service Representative');

INSERT INTO user_role(user_id, role_id) VALUES
                                            (1, 1), -- give admin ROLE_ADMIN
                                            (2, 2),  -- give Jane ROLE_CSR
                                            (3, 2),  -- give Mark ROLE_CSR
                                            (4, 1),  -- give Wally ROLE_ADMIN
                                            (4, 2);  -- give Wally ROLE_CSR