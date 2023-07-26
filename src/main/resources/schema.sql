CREATE TABLE easy_drinks (
	id INT NOT NULL Primary Key AUTO_INCREMENT, 
	name VARCHAR(255), 
	main1 VARCHAR(255), 
	amount1 double, 
	main2 VARCHAR(255), 
	amount2 double, 
	directions VARCHAR(255));

create table SEC_USER
(
	userId           BIGINT NOT NULL Primary Key AUTO_INCREMENT,
	userName         VARCHAR(36) NOT NULL UNIQUE,
	encryptedPassword VARCHAR(128) NOT NULL,
	ENABLED           BIT NOT NULL
) ;


create table SEC_ROLE
(
	roleId   BIGINT NOT NULL Primary Key AUTO_INCREMENT,
	roleName VARCHAR(30) NOT NULL UNIQUE

) ;
create table USER_ROLE
(
	ID      BIGINT NOT NULL Primary Key AUTO_INCREMENT,
	userId BIGINT NOT NULL,
	roleId BIGINT NOT NULL
);
alter table USER_ROLE
	add constraint USER_ROLE_UK unique (userId, roleId);

alter table USER_ROLE
	add constraint USER_ROLE_FK1 foreign key (userId)
		references SEC_USER (userId);

alter table USER_ROLE
	add constraint USER_ROLE_FK2 foreign key (roleId)
		references SEC_ROLE (roleId);

