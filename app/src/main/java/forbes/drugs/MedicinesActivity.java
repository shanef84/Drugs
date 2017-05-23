package forbes.drugs;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MedicinesActivity extends AppCompatActivity {

    private ListView mMedicinesListView;
    private MedicinesAdapter mMedicinesAdapter;
    private ProgressBar mProgressBar;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private ChildEventListener mChildEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines);

        //FirebaseDatabase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("Products");

        mMedicinesListView = (ListView) findViewById(R.id.medicinesListView);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        // Initialize message ListView and its adapter
        final List<Medicines> medicinesList = new ArrayList<>();
        mMedicinesAdapter = new MedicinesAdapter(this, R.layout.item_medicine, medicinesList);
        mMedicinesListView.setAdapter(mMedicinesAdapter);

        // Initialize progress bar
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);

        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Medicines medicines = dataSnapshot.getValue(Medicines.class);
                mMedicinesAdapter.add(medicines);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mMessagesDatabaseReference.addChildEventListener(mChildEventListener);

        mMedicinesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent_hpra = new Intent(MedicinesActivity.this, MedWebActivity.class);
                Bundle bundle = new Bundle();
                Medicines med = (Medicines) parent.getItemAtPosition(position);
                String pano = med.getLicenseNumber();
                Snackbar.make(view, pano, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                bundle.putString("URL", "http://www.hpra.ie/homepage/medicines/medicines-information/find-a-medicine/results/item?pano=" + pano);
                intent_hpra.putExtras(bundle);
                startActivity(intent_hpra);
            }
        });


    }
}
