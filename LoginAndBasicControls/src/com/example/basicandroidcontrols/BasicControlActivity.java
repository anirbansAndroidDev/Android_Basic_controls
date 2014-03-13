package com.example.basicandroidcontrols;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class BasicControlActivity extends Activity {

	EditText editTextDatePicker;
	EditText editTextTimePicker;
	
	Spinner simpleSpinner;
	Spinner nameAndValueSpinner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic_control);

		editTextDatePicker 	= (EditText)findViewById(R.id.editTextYearMade);
		editTextTimePicker 	= (EditText)findViewById(R.id.editTextStartingRentalTime);

		simpleSpinner 		= (Spinner)findViewById(R.id.spinnerVehicleType);
		nameAndValueSpinner = (Spinner)findViewById(R.id.nameAndValueSpinner);
		
		populateSpinner();
	}
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//name value pair Spinner 
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	private void populateSpinner() 
	{
		final MyData items[] = new MyData[3];
        items[0] = new MyData( "Car","1" );
        items[1] = new MyData( "Van","2" );
        items[2] = new MyData( "Bike","3" );
        
        ArrayAdapter<MyData> adapter = new ArrayAdapter<MyData>( this,android.R.layout.simple_spinner_item,items );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nameAndValueSpinner.setAdapter(adapter);
        nameAndValueSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() 
        {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) 
                {
                    MyData d = items[position];
            		Toast.makeText(BasicControlActivity.this,"Value: " + d.getValue() + " Name: " + d.getSpinnerText(), Toast.LENGTH_LONG).show();  
                }

                public void onNothingSelected(AdapterView<?> parent) 
                {
                	//On Nothing selection do something
                }
            }
        );
	}
	class MyData 
    {
        public MyData( String spinnerText, String value ) 
        {
            this.spinnerText = spinnerText;
            this.value = value;
        }

        public String getSpinnerText() {
            return spinnerText;
        }

        public String getValue() {
            return value;
        }

        public String toString() {
            return spinnerText;
        }

        String spinnerText;
        String value;
    }
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//END name value pair Spinner 
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//Date picker
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	public void datePicker(View v) 
	{
		Calendar mcurrentDate=Calendar.getInstance();
		int mYear=mcurrentDate.get(Calendar.YEAR);
		int mMonth=mcurrentDate.get(Calendar.MONTH);
		int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

		DatePickerDialog mDatePicker=new DatePickerDialog(BasicControlActivity.this, new OnDateSetListener() 
		{                  
			public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) 
			{
				String selectedmonthModified = (selectedmonth+1)/10==0?("0"+(selectedmonth+1)): String.valueOf((selectedmonth+1));

				editTextDatePicker.setText(new StringBuilder()
										.append(selectedday).append("-")
										.append(selectedmonthModified).append("-")
										.append(selectedyear).append(" "));
			}
		},mYear, mMonth, mDay);
		mDatePicker.setTitle("Select date");                
		mDatePicker.show();  
	}
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//END Date picker
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//Time picker
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	public void timePickerStartingRental(View v) 
	{
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(BasicControlActivity.this, new TimePickerDialog.OnTimeSetListener() 
        {
        	 @Override
             public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) 
             {
             	String selectedMinuteStr = selectedMinute + "";
             	if(selectedMinuteStr.length()<2)
             	{
             		selectedMinuteStr = 0 + selectedMinuteStr;
             	}
             	
             	String selectedHourStr = selectedHour + "";
             	if(selectedHourStr.length()<2)
             	{
             		selectedHourStr = 0 + selectedHourStr;
             	}
             	
             	editTextTimePicker.setText( "" + selectedHourStr + ":" + selectedMinuteStr);
        }, hour, minute, true);
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//END Time picker
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

}
