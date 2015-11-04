package com.example.goeurotest;

import java.util.Calendar;

import com.example.adapter.SuggestionAdapter;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
	private AutoCompleteTextView startDestinationTextView;
	private AutoCompleteTextView endDestinationTextView;
	private EditText manualDateEditText;
	private ImageView searchButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startDestinationTextView = (AutoCompleteTextView) findViewById(R.id.start_destination);
		startDestinationTextView.setAdapter(new SuggestionAdapter(this,
				startDestinationTextView.getText().toString()));

		endDestinationTextView = (AutoCompleteTextView) findViewById(R.id.end_destination);
		endDestinationTextView.setAdapter(new SuggestionAdapter(this,
				endDestinationTextView.getText().toString()));

		searchButton = (ImageView) findViewById(R.id.searchbutton);
		searchButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(),
						"Search is not yet implemented", Toast.LENGTH_LONG)
						.show();

			}
		});
	}

	public void selectDate(View view) {
		DialogFragment newFragment = new SelectDateFragment();
		newFragment.show(getSupportFragmentManager(), "DatePicker");
	}

	public void populateSetDate(int year, int month, int day) {
		manualDateEditText = (EditText) findViewById(R.id.editText1);
		manualDateEditText.setText(month + "/" + day + "/" + year);
	}

	@SuppressLint("ValidFragment")
	public class SelectDateFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			final Calendar calendar = Calendar.getInstance();
			int yy = calendar.get(Calendar.YEAR);
			int mm = calendar.get(Calendar.MONTH);
			int dd = calendar.get(Calendar.DAY_OF_MONTH);
			return new DatePickerDialog(getActivity(), this, yy, mm, dd);
		}

		public void onDateSet(DatePicker view, int yy, int mm, int dd) {
			populateSetDate(yy, mm + 1, dd);
		}
	}
}
