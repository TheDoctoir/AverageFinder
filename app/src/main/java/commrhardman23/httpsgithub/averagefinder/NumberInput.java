package commrhardman23.httpsgithub.averagefinder;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class NumberInput extends AppCompatActivity {

    private static int numElementsAdded;
    private double[] numbersToAverage;
    private EditText edtxtUserNumber;
    private TextView txtvwResult;
    private TextView txtvwAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_input);

        edtxtUserNumber = (EditText) findViewById(R.id.edtxtUserNumber);
        txtvwResult = (TextView) findViewById(R.id.txtvwResult);
        txtvwAverage = (TextView) findViewById(R.id.txtvwAverage);

        numElementsAdded = 0;
        numbersToAverage = new double[20];
    }

    /**
     * addNumToArray adds the user's number to the array at the current index and
     * increments numElementsAdded by one
     * @param vw is the button this method is associated with
     */
    public void addNumToArray(View vw){

        //converts the text entered by the user into a double
        double userInput = Double.parseDouble(edtxtUserNumber.getText().toString());

        //must check to make sure we haven't added too many numbers yet
        if(numElementsAdded < numbersToAverage.length) {

            //we haven't so we place the new number at the current index
            numbersToAverage[numElementsAdded] = userInput;
            //then increase the index by one so the next number will go in the next slot
            numElementsAdded++;
            //this lets the user know the addition was successful
            txtvwResult.setText("Number Added Successfully");

        } else {

            //this lets the user know they've reached the end of the array
            txtvwResult.setText("You have inserted the maximum amount of numbers");

        }

        //this code clears the numbers inputted by the user
        edtxtUserNumber.setText("");

        //this code closes the keyboard
        InputMethodManager inputManager = (InputMethodManager)
                this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(vw.getApplicationWindowToken(), 0);

    }

    /**
     * displayAverage changes the text in txtvwAverage to display the decimal average
     * @param vw is the button this method is associate with
     */
    public void displayAverage(View vw){

        //calls the method that will complete the average calculations
        double average = calcAverage();

        //displays the calculated average received from the calcAverage method
        txtvwResult.setText("The average of the numbers is: " + average);

    }

    /**
     * calcAverage is called by displayAverage to do all the average calculations and
     * return the value back to displayAverage so that it can be displayed to the user
     * @return a double that is the average of the numbers inserted by the user
     */
    private double calcAverage(){

        //defining sum and average variables to do calculations
        double sum = 0.0;
        double average = 0.0;

        /**
         * have to test to make sure elements were added or program will end up dividing by zero
         * if no elements are entered
         */
        if(numElementsAdded > 0) {

            //iterates through elements in the array to sum all the numbers
            for (int i = 0; i < numElementsAdded; i++) {

                //adds numbersToAverage at index i to what the sum already is
                sum += numbersToAverage[i];

            }

            /**
             * takes the sum and divides it by the number of elements * 1.0
             * Note: if you don't know why I multiplied by 1.0, test the program with
             *       a specific set of numbers that will have a decimal average. Then,
             *       remove the "* 1.0" and see what changes. You may ask me why this
             *       is the case once you have done it and want to know me
             */
            average = sum / (numElementsAdded * 1.0);

        }

        //returns the calculated average to the method caller
        return average;

    }
}
