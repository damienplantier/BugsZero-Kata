package com.adaptionsoft.games.uglytrivia;

public class Question {
    
    private Category category;

    public Question(Category cat) {
        category = cat;
    }

    public Category getCategory() {
        return category;
    }
}
