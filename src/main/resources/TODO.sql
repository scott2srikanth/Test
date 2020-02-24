CREATE TABLE "todo"."login" (
    "user_id" serial DEFAULT nextval('1'::regclass),
    "username" varchar(100),
    "password" varchar(20),
    "email" varchar(300),
    UNIQUE ("user_id"),
    PRIMARY KEY ("username")
);