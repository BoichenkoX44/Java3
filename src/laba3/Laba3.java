/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laba3;

      
/**
 *
 * @author 1710504
 */

   
  
public class Laba3 {
    

        /**
         *
         * @param args
         */
        public static void main(String[] args) {
            int[] nums = { 5, -6, 2, 0, -2, 4 };
            System.out.println(smallestMissingNumberInArray(nums));
        }
        public static int smallestMissingNumberInArray(int[] nums) {
            if (nums == null) {
                throw new IllegalArgumentException("Array cannot be null!");
            }
            int n = nums.length;
            if (n == 0) {
                throw new IllegalArgumentException("Array cannot be empty!");
            }
            int min = nums[0];
            int max = min;
            for (int i = 1; i < n; ++i) {
                min = Math.min(min, nums[i]);
                max = Math.max(max, nums[i]);
            }
            int sqrtN = (int)Math.ceil(Math.sqrt(n));
            int bucketSize = (int)Math.ceil(n / Math.sqrt(n));
            int[] buckets = new int[sqrtN];
            for (int i = 0; i < n; ++i) {
                int normalizedNum = nums[i] - min;
                int bucket = normalizedNum / bucketSize;
                if (bucket < sqrtN) {
                    ++buckets[bucket];
                }
            }
            int bucketWithMissingNumber = 0;
            for (int i = 0; i < sqrtN; ++i) {
                if (buckets[i] < bucketSize) {
                    bucketWithMissingNumber = i;
                    break;
                }
            }
            int bucketMin = bucketSize * bucketWithMissingNumber;
            int bucketMax = bucketMin + sqrtN;
            int[] bucketNums = new int[sqrtN];
            int numValuesInBucket = 0;
            for (int i = 0; i < n; ++i) {
                int normalizedNum = nums[i] - min;
                if (normalizedNum >= bucketMin && normalizedNum <= bucketMax) {
                    bucketNums[numValuesInBucket++] = normalizedNum;
                }
            }
            for (int i = bucketMin; i <= bucketMax; ++i) {
                boolean foundNumber = false;
                for (int j = 0; j < numValuesInBucket; ++j) {
                    if (i == bucketNums[j]) {
                        foundNumber = true;
                    }
                }
                if (!foundNumber) {
                    int denormalizedAnswer = i + min;
                    if (denormalizedAnswer <= min || denormalizedAnswer >= max) {
                        throw new IllegalArgumentException("Array is not missing a number!");
                    }
                    return denormalizedAnswer;
                }
            }
            throw new IllegalArgumentException("Array is not missing a number!");
        }
    }
  


    
