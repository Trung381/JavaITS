CREATE TABLE blacklisted_token
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    token          VARCHAR(255) NOT NULL,
    blacklisted_at datetime     NOT NULL,
    CONSTRAINT pk_blacklistedtoken PRIMARY KEY (id)
);

ALTER TABLE blacklisted_token
    ADD CONSTRAINT uc_blacklistedtoken_token UNIQUE (token);
CREATE TABLE permission
(
    id            INT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(50)        NOT NULL,
    `description` VARCHAR(255)       NULL,
    CONSTRAINT pk_permission PRIMARY KEY (id)
);

ALTER TABLE permission
    ADD CONSTRAINT uc_permission_name UNIQUE (name);
CREATE TABLE `role`
(
    id   INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(50)        NOT NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE role_permission
(
    permission_id INT NOT NULL,
    role_id       INT NOT NULL,
    CONSTRAINT pk_role_permission PRIMARY KEY (permission_id, role_id)
);

ALTER TABLE `role`
    ADD CONSTRAINT uc_role_name UNIQUE (name);

ALTER TABLE role_permission
    ADD CONSTRAINT fk_rolper_on_permission FOREIGN KEY (permission_id) REFERENCES permission (id);

ALTER TABLE role_permission
    ADD CONSTRAINT fk_rolper_on_role FOREIGN KEY (role_id) REFERENCES `role` (id);
CREATE TABLE tier_config
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    min_usage INT                   NOT NULL,
    max_usage INT                   NOT NULL,
    price     DOUBLE                NOT NULL,
    CONSTRAINT pk_tierconfig PRIMARY KEY (id)
);
CREATE TABLE usage_history
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    date    date                  NULL,
    e_used  INT                   NOT NULL,
    amount  DOUBLE                NOT NULL,
    status  INT                   NOT NULL,
    user_id INT                   NULL,
    CONSTRAINT pk_usagehistory PRIMARY KEY (id)
);

ALTER TABLE usage_history
    ADD CONSTRAINT FK_USAGEHISTORY_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);
CREATE TABLE user
(
    id        INT AUTO_INCREMENT NOT NULL,
    firstname VARCHAR(255)       NULL,
    lastname  VARCHAR(255)       NULL,
    email     VARCHAR(255)       NULL,
    username  VARCHAR(255)       NULL,
    address   VARCHAR(255)       NULL,
    password  VARCHAR(255)       NULL,
    role_id   INT                NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE user
    ADD CONSTRAINT FK_USER_ON_ROLE FOREIGN KEY (role_id) REFERENCES `role` (id);
CREATE TABLE role_permission
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    role_id       INT                   NOT NULL,
    permission_id INT                   NOT NULL,
    CONSTRAINT pk_role_permission PRIMARY KEY (id)
);