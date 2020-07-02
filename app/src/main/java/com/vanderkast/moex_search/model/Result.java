package com.vanderkast.moex_search.model;

public class Result {
    private Result() {
    }

    public static class Success extends Result {
    }

    public static class Failure extends Result {
        private Exception description;

        public Failure(Exception description) {
            this.description = description;
        }

        public Exception getDescription() {
            return description;
        }
    }
}
