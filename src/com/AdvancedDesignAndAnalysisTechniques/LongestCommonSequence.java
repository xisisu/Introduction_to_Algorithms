package com.AdvancedDesignAndAnalysisTechniques;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Dynamic programming, two dimensional array to record temp results
 *
 * Created by xisisu on 11/6/16.
 */
public class LongestCommonSequence {
    public String longestCommonSequence(final String s1, final String s2) {
        final Integer m = s1.length();
        final Integer n = s2.length();
        if (m == 0 || n == 0) { return ""; }

        final int[][] dp = new int[m+1][n+1];
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        // now print the result string
        int i = m, j = n;
        StringBuilder result = new StringBuilder();
        while (i > 0 && j > 0) {
            if ( (dp[i][j] == dp[i-1][j-1]+1) &&
                 s1.charAt(i-1) == s2.charAt(j-1) )
            {
                result.append(s1.charAt(i-1));
                --i; --j;
            } else {
                if (dp[i][j] == dp[i-1][j]) {
                    --i;
                } else {
                    --j;
                }
            }
        }

        return result.reverse().toString();
    }

    @Test public void test() {
        final String s1 = "ACCGGTCGAGTGCGCGGAAGCCGGCCGAA";
        final String s2 = "GTCGTTCGGAATGCCGTTGCTCTGTAAA";
        Assert.assertEquals("GTCGTCGGAAGCCGGCCGAA", longestCommonSequence(s1, s2));
    }

    @Test public void testSimple() {
        final String s1 = "BDCABA";
        final String s2 = "ABCBDAB";
        // both BDBA and BCBA works
        System.out.println(longestCommonSequence(s1, s2));
    }
}
