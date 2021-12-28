.DEFAULT_GOAL := help

.git/hooks/pre-commit:
	@echo "make test" > $@
	@chmod +x $@

.PHONY: help
help:
	@grep '^[a-zA-Z]' $(MAKEFILE_LIST) | sort | awk -F ':.*?## ' 'NF==2 {printf "\033[36m  %-25s\033[0m %s\n", $$1, $$2}'

.PHONY: setup
setup: .git/hooks/pre-commit test ## setup development environment (run this if you have just cloned the repository)

.PHONY: test
test: ## test all katas
	clj -M:test

.PHONY: fizzbuzz
fizzbuzz: ## run fizzbuzz solution
	clj -X fizzbuzz/-main

.PHONY: lcd-number
lcd-number: ## run lcd-number solution
	clj -X lcd-number/-main

.PHONY: tic-tac-toe
tic-tac-toe: ## run tic-tac-toe solution
	clj -X tic-tac-toe/-main

