include .env
SHELL := bash

up:
	@(docker-compose up -d)

ps:
	@(docker-compose ps)

migrate-up:
	psql "postgres://${DATABASE_NAME}:${DATABASE_PASSWORD}@${DATABASE_HOST}:${DATABASE_PORT}/${DATABASE_NAME}" -a -f ./migrations/1678007739_create_init_tables_up.sql

down:
	@(docker-compose down -v --remove-orphans)