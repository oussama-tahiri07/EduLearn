package com.example.edulearn.Sidebar;//package com.example.edulearn.moodle.Sidebar;
// LMAO XD
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.TextView;
//
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.EventListener;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.FirebaseFirestoreException;
//
//import com.example.edulearn.moodle.R;
//import com.example.edulearn.moodle.SignInUp.LoginActivity;
//
//public class NavHeader extends AppCompatActivity {
//    FirebaseAuth mAuth;
//    FirebaseUser fUser;
//    TextView mEmail;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        mEmail = (TextView) findViewById(R.id.email123123);;
//        mAuth = FirebaseAuth.getInstance();
//
////        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        fUser = mAuth.getCurrentUser();
//
//        if (fUser != null) {
//            mEmail.setText(fUser.getEmail());
////            DocumentReference userRef = db.collection("users").document(fUser.getUid());
////            userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
////                @Override
////                public void onSuccess(DocumentSnapshot documentSnapshot) {
////                    if (documentSnapshot.exists()) {
////                        String userEmail = documentSnapshot.getString("email");
////                        mEmail.setText(userEmail); // Set the user's email in the TextView
////                    } else {
////                        Log.d("NavHeader", "User document does not exist in Firestore");
////                    }
////                }
////            }).addOnFailureListener(new OnFailureListener() {
////                @Override
////                public void onFailure(@NonNull Exception e) {
////                    Log.e("NavHeader", "Error getting user document from Firestore", e);
////                }
////            });
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.nav_header);
//        } else {
//            Log.d("NavHeader", "User is not signed in");
//            mEmail.setText(""); // You can set a default text
//        }
//    }
//}