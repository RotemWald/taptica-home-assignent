package com.taptica.rotemwald.controllers;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * GenericController manages all general-based requests
 */
@RestController
public class GenericController {

    /**
     * Hello World route
     * @return hello world message
     */
    @RequestMapping(value="/hello", method=RequestMethod.GET)
    public String greeting() {
        return "Hello World";
    }

    /**
     * Fibonacci n-th element calculator route
     * @param n number of requested sequence element
     * @return requested fibonacci series number by sequence number
     */
    @RequestMapping(value="/fibo", method=RequestMethod.GET)
    public int fibonacci(@RequestParam(value="n", defaultValue="0") int n) {
        return fib(n);
    }

    /**
     * Finds the n-th fibonacci number (GeeksForGeeks)
     * @param n sequence number
     * @return n-th fibonacci number
     */
    private static int fib(int n) {
        /* Declare an array to store Fibonacci numbers. */
        int f[] = new int[n+2]; // 1 extra to handle case, n = 0
        int i;

        /* 0th and 1st number of the series are 0 and 1 */
        f[0] = 0;
        f[1] = 1;

        for (i = 2; i <= n; i++) {
            /* Add the previous 2 numbers in the series and store it */
            f[i] = f[i-1] + f[i-2];
        }

        return f[n];
    }
}
