package com.google.network;

/**
 * Created by apple on 16/10/4.
 */
public class DefaultRetryPolicy implements RetryPolicy {

    /**
     * The default socket timeout in milliseconds
     */
    public static final int DEFAULT_TIMEOUT_MS = 6000;
    /**
     * The default number of retries
     */
    public static final int DEFAULT_MAX_RETRIES = 2;
    /**
     * The default backoff multiplier
     */
    public static final float DEFAULT_BACKOFF_MULT = 1f;
    /**
     * The maximum number of attempts.
     */
    private final int mMaxNumRetries;
    /**
     * The current timeout in milliseconds.
     */
    private int mCurrentTimeoutMs;
    /**
     * The current retry count.
     */
    private int mCurrentRetryCount;

    /**
     * Constructs a new retry policy using the default timeouts.
     */
    public DefaultRetryPolicy() {
        this(DEFAULT_TIMEOUT_MS, DEFAULT_MAX_RETRIES);
    }

    /**
     * Constructs a new retry policy.
     *
     * @param initialTimeoutMs  The initial timeout for the policy.
     * @param maxNumRetries     The maximum number of retries.
     *
     */
    public DefaultRetryPolicy(int initialTimeoutMs, int maxNumRetries) {
        mCurrentTimeoutMs = initialTimeoutMs;
        mMaxNumRetries = maxNumRetries;
    }


    @Override
    public int getCurrentTimeOut() {
        return mCurrentTimeoutMs;
    }

    @Override
    public int getCurrentRetryCount() {
        return mCurrentRetryCount;
    }

    @Override
    public void retry(VolleyError e) throws VolleyError{
        mCurrentRetryCount++;
        mCurrentTimeoutMs += mCurrentTimeoutMs;
        System.out.println("currentRetryCount:"+mCurrentRetryCount);
        if (!hasAttemptRemaining()) {
            throw e;
        }
    }
    protected boolean hasAttemptRemaining() {
        return mCurrentRetryCount < mMaxNumRetries;
    }
}
