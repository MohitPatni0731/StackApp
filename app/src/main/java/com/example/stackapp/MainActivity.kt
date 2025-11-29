package com.example.stackapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

/**
 * MainActivity for StackApp
 * 
 * Provides a user interface to interact with a custom stack implementation.
 * Supports three operations:
 * - PUSH: Add an integer (0-9) to the stack
 * - POP: Remove the top element from the stack
 * - QUIT: Exit the application
 * 
 * The app displays operation results and current stack state after each action.
 */
class MainActivity : AppCompatActivity() {
    
    // Custom stack instance
    private lateinit var stack: Stack
    
    // UI Elements
    private lateinit var inputEditText: EditText
    private lateinit var commandEditText: EditText
    private lateinit var outputTextView: TextView
    private lateinit var stackDisplayTextView: TextView
    private lateinit var scrollView: ScrollView
    private lateinit var pushButton: Button
    private lateinit var popButton: Button
    private lateinit var executeButton: Button
    private lateinit var quitButton: Button
    private lateinit var clearButton: Button
    
    // StringBuilder to accumulate output history
    private val outputHistory = StringBuilder()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // Initialize the custom stack
        stack = Stack()
        
        // Initialize UI elements
        initializeViews()
        
        // Set up button click listeners
        setupButtonListeners()
        
        // Display initial state
        updateStackDisplay()
        appendOutput("Welcome to StackApp!")
        appendOutput("Commands: 'push X' (X: 0-9), 'pop', 'quit'")
        appendOutput("Or use the buttons below.")
        appendOutput("─".repeat(40))
    }
    
    /**
     * Initialize all view references
     */
    private fun initializeViews() {
        inputEditText = findViewById(R.id.inputEditText)
        commandEditText = findViewById(R.id.commandEditText)
        outputTextView = findViewById(R.id.outputTextView)
        stackDisplayTextView = findViewById(R.id.stackDisplayTextView)
        scrollView = findViewById(R.id.scrollView)
        pushButton = findViewById(R.id.pushButton)
        popButton = findViewById(R.id.popButton)
        executeButton = findViewById(R.id.executeButton)
        quitButton = findViewById(R.id.quitButton)
        clearButton = findViewById(R.id.clearButton)
    }
    
    /**
     * Set up click listeners for all buttons
     */
    private fun setupButtonListeners() {
        // PUSH Button - pushes the value from inputEditText
        pushButton.setOnClickListener {
            handlePushFromInput()
        }
        
        // POP Button - pops the top element
        popButton.setOnClickListener {
            handlePop()
        }
        
        // EXECUTE Button - parses and executes text command
        executeButton.setOnClickListener {
            handleTextCommand()
        }
        
        // QUIT Button - exits the application
        quitButton.setOnClickListener {
            handleQuit()
        }
        
        // CLEAR Button - clears output history
        clearButton.setOnClickListener {
            clearOutput()
        }
    }
    
    /**
     * Handle push operation from the number input field
     */
    private fun handlePushFromInput() {
        val input = inputEditText.text.toString().trim()
        
        if (input.isEmpty()) {
            showError("Please enter a value (0-9) to push.")
            updateStackDisplay()
            return
        }
        
        val value = input.toIntOrNull()
        
        if (value == null) {
            showError("Invalid input: '$input'. Please enter an integer (0-9).")
            updateStackDisplay()
            inputEditText.text.clear()
            return
        }
        
        // Perform push operation
        val result = stack.push(value)
        displayResult(result)
        inputEditText.text.clear()
    }
    
    /**
     * Handle pop operation
     */
    private fun handlePop() {
        val result = stack.pop()
        displayResult(result)
    }
    
    /**
     * Handle text command input (e.g., "push 5", "pop", "quit")
     */
    private fun handleTextCommand() {
        val command = commandEditText.text.toString().trim().lowercase()
        
        if (command.isEmpty()) {
            showError("Please enter a command (push X, pop, or quit).")
            updateStackDisplay()
            return
        }
        
        // Parse and execute the command
        when {
            command == "pop" -> {
                appendOutput("Input: pop")
                handlePop()
            }
            command == "quit" -> {
                appendOutput("Input: quit")
                handleQuit()
            }
            command.startsWith("push") -> {
                appendOutput("Input: $command")
                handlePushCommand(command)
            }
            else -> {
                appendOutput("Input: $command")
                showError("Invalid command: '$command'. Use 'push X', 'pop', or 'quit'.")
                updateStackDisplay()
            }
        }
        
        commandEditText.text.clear()
    }
    
    /**
     * Parse and handle the push command from text input
     * Expected format: "push X" where X is 0-9
     */
    private fun handlePushCommand(command: String) {
        val parts = command.split("\\s+".toRegex())
        
        if (parts.size < 2) {
            showError("Push requires a value. Usage: 'push X' where X is 0-9.")
            updateStackDisplay()
            return
        }
        
        if (parts.size > 2) {
            showError("Invalid push format. Usage: 'push X' where X is 0-9.")
            updateStackDisplay()
            return
        }
        
        val valueStr = parts[1]
        val value = valueStr.toIntOrNull()
        
        if (value == null) {
            showError("Invalid value: '$valueStr'. Must be an integer (0-9).")
            updateStackDisplay()
            return
        }
        
        // Perform push operation
        val result = stack.push(value)
        displayResult(result)
    }
    
    /**
     * Handle quit operation - exit the application
     */
    private fun handleQuit() {
        appendOutput("Exiting StackApp. Goodbye!")
        // Finish the activity to close the app
        finish()
    }
    
    /**
     * Display the result of a stack operation
     */
    private fun displayResult(result: StackResult) {
        if (result.success) {
            appendOutput("✓ ${result.message} ${result.stackState}")
        } else {
            appendOutput("✗ ${result.message} ${result.stackState}")
        }
        updateStackDisplay()
    }
    
    /**
     * Show an error message in the output
     */
    private fun showError(message: String) {
        appendOutput("✗ Error: $message")
    }
    
    /**
     * Append a message to the output history and update display
     */
    private fun appendOutput(message: String) {
        outputHistory.append(message).append("\n")
        outputTextView.text = outputHistory.toString()
        
        // Auto-scroll to bottom
        scrollView.post {
            scrollView.fullScroll(View.FOCUS_DOWN)
        }
    }
    
    /**
     * Update the stack display to show current contents
     */
    private fun updateStackDisplay() {
        stackDisplayTextView.text = stack.getStackString()
        
        // Visual feedback: change color based on stack state
        val colorRes = when {
            stack.isFull() -> R.color.stack_full
            stack.isEmpty() -> R.color.stack_empty
            else -> R.color.stack_normal
        }
        stackDisplayTextView.setTextColor(ContextCompat.getColor(this, colorRes))
    }
    
    /**
     * Clear the output history
     */
    private fun clearOutput() {
        outputHistory.clear()
        outputTextView.text = ""
        appendOutput("Output cleared.")
        appendOutput("─".repeat(40))
    }
}

