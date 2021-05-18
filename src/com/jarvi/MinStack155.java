package com.jarvi;

import java.util.Deque;
import java.util.LinkedList;

public class MinStack155 {

    private Deque<Integer> data;

    private Deque<Integer> min;

    public MinStack155() {
        data = new LinkedList<>();
        min = new LinkedList<>();
    }

    public void push(int val) {
        data.push(val);
        if (min.isEmpty() || min.peek() >= val) {
            min.push(val);
        }
    }

    public void pop() {
        int top = data.pop();
        if (top == min.peek()) {
            min.pop();
        }
    }

    public int top() {
        return data.peek();
    }

    public int getMin() {
        return min.peek();
    }

}
