package com.example.stackapp

/**
 * Custom Stack Implementation using an array and top index.
 * This is a LIFO (Last In First Out) data structure.
 * 
 * - Maximum capacity: 3 elements
 * - Allowed values: integers 0-9
 * - Top of Stack (ToS) is the rightmost element when displayed
 */
class Stack {
    
    // Fixed-size array to hold stack elements (max 3)
    private val elements = IntArray(MAX_SIZE)
    
    // Index pointing to the top of the stack (-1 means empty)
    private var top: Int = -1
    
    companion object {
        const val MAX_SIZE = 3      // Maximum stack capacity
        const val MIN_VALUE = 0     // Minimum allowed value
        const val MAX_VALUE = 9     // Maximum allowed value
    }
    
    /**
     * Check if the stack is empty
     * @return true if stack has no elements
     */
    fun isEmpty(): Boolean {
        return top == -1
    }
    
    /**
     * Check if the stack is full
     * @return true if stack has reached MAX_SIZE elements
     */
    fun isFull(): Boolean {
        return top == MAX_SIZE - 1
    }
    
    /**
     * Get the current number of elements in the stack
     * @return count of elements
     */
    fun size(): Int {
        return top + 1
    }
    
    /**
     * Push an element onto the stack
     * @param value the integer value to push (must be 0-9)
     * @return PushResult indicating success or failure with message
     */
    fun push(value: Int): StackResult {
        // Validate value range
        if (value < MIN_VALUE || value > MAX_VALUE) {
            return StackResult(
                success = false,
                message = "Invalid value: $value. Must be between $MIN_VALUE and $MAX_VALUE.",
                stackState = getStackString()
            )
        }
        
        // Check if stack is full
        if (isFull()) {
            return StackResult(
                success = false,
                message = "Stack is FULL.",
                stackState = getStackString()
            )
        }
        
        // Push the value: increment top and add element
        top++
        elements[top] = value
        
        return StackResult(
            success = true,
            message = "$value is pushed.",
            stackState = getStackString()
        )
    }
    
    /**
     * Pop an element from the stack
     * @return StackResult indicating success or failure with message
     */
    fun pop(): StackResult {
        // Check if stack is empty
        if (isEmpty()) {
            return StackResult(
                success = false,
                message = "Stack is EMPTY.",
                stackState = getStackString()
            )
        }
        
        // Pop the value: get element and decrement top
        val poppedValue = elements[top]
        top--
        
        return StackResult(
            success = true,
            message = "$poppedValue is popped.",
            stackState = getStackString()
        )
    }
    
    /**
     * Peek at the top element without removing it
     * @return the top element or null if empty
     */
    fun peek(): Int? {
        return if (isEmpty()) null else elements[top]
    }
    
    /**
     * Get string representation of stack contents
     * Format: "Stack [elem1 elem2 elem3]" where rightmost is ToS
     * @return formatted stack string
     */
    fun getStackString(): String {
        if (isEmpty()) {
            return "Stack []"
        }
        
        val builder = StringBuilder("Stack [")
        for (i in 0..top) {
            builder.append(elements[i])
            if (i < top) {
                builder.append(" ")
            }
        }
        builder.append("]")
        return builder.toString()
    }
}

/**
 * Data class to represent the result of a stack operation
 */
data class StackResult(
    val success: Boolean,      // Whether the operation succeeded
    val message: String,       // Description of what happened
    val stackState: String     // Current stack contents as string
)

