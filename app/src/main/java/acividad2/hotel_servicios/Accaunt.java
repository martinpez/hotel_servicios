package acividad2.hotel_servicios;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.widget.Button;
import android.widget.Spinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import acividad2.hotel_servicios.data.HotelDBHelper;
import acividad2.hotel_servicios.data.Huesped;
import acividad2.hotel_servicios.data.Telefono;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Accaunt#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Accaunt extends Fragment implements View.OnClickListener {

    private HotelDBHelper db;
    private ImageView img_entry_acc;
    private Spinner spinner_num;
    private EditText input_name , input_id , input_email , input_phone ,input_password ;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Accaunt() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Reservation.
     */
    // TODO: Rename and change types and number of parameters
    public static Accaunt newInstance(String param1, String param2) {
        Accaunt fragment = new Accaunt();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.accaunt, container, false);
    }
    Bundle bundle = new Bundle();
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // EDIT TEXT
        input_email = view.findViewById(R.id.input_email);
        input_phone = view.findViewById(R.id.input_phone);
        input_password = view.findViewById(R.id.input_password);
        input_id = view.findViewById(R.id.input_id);
        input_name = view.findViewById(R.id.input_name);

        // SPINNER
        spinner_num = view.findViewById(R.id.spinner_num);

        // IMAGE VIEW
        img_entry_acc = view.findViewById(R.id.img_entry_acc);
        img_entry_acc.setOnClickListener(this);

        db = new HotelDBHelper(getContext());
    }

    @Override
    public void onClick(View view) {
        try {
            // GUARDAR LOS EDITTEXT EN UN STRING
            String nombre = input_name.getText().toString().trim();
            String id = input_id.getText().toString().trim();
            int id_2 = Integer.parseInt(id);

            String email = input_email.getText().toString().trim();
            String telephone = spinner_num.getSelectedItem().toString() + input_phone.getText().toString().trim();
            String password = input_password.getText().toString().trim();

            // Verify that all fields are not empty
            if (nombre.isEmpty() || id.isEmpty() || email.isEmpty() || telephone.isEmpty() || password.isEmpty()) {
                throw new Exception();
            }

            Cursor cursor2 = db.getHuespedByUser(email, password);
            if (!cursor2.moveToNext()) {
                Huesped datos = new Huesped(nombre, id_2, email, password);
                Telefono datos2 = new Telefono(id_2, telephone);
                db.saveHuesped(datos, datos2);
                //bundle.putString("Telefono" , input_phone.getText().toString());
                Toast.makeText(getContext(), "Se registro exitosamente", Toast.LENGTH_LONG).show();
                Navigation.findNavController(view).navigate(R.id.login, bundle);
            } else {
                Toast.makeText(getContext(), "Credenciales invalidas.", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            // Set error messages for all EditText fields when any field is empty
            input_name.setError("Empty fields");
            input_id.setError("Empty fields");
            input_name.setError("Empty fields");
            input_email.setError("Empty fields");
            input_phone.setError("Empty fields");
            input_password.setError("Empty fields");
            e.printStackTrace();
        }

    }
        /*try {
            // GUARDAR LOS EDITTEXT EN UN STRING
            String nombre = input_name.getText().toString().trim();
            String id = input_id.getText().toString().trim();
            String email = input_email.getText().toString().trim();
            String telephone = spinner_num.getSelectedItem().toString() + input_phone.getText().toString().trim();
            String password = input_password.getText().toString().trim();

            int id_2 = Integer.parseInt(id);

            Cursor cursor2 = db.getHuespedByUser(email, password);
            if (!cursor2.moveToNext()) {
                System.out.println("name" + input_name.getText());
                Huesped datos = new Huesped(nombre, id_2, email, password);
                Telefono datos2 = new Telefono(id_2, telephone);
                db.saveHuesped(datos, datos2);
                Toast.makeText(getContext(), "Se registro exitosamente", Toast.LENGTH_LONG).show();
                Navigation.findNavController(view).navigate(R.id.login);
            }

        } catch (Exception e) {
            input_name.setError("Empty fields");
            input_id.setError("Empty fields");
            input_name.setError("Empty fields");
            input_email.setError("Empty fields");
            input_phone.setError("Empty fields");
            input_password.setError("Empty fields");
            e.printStackTrace();
            Toast.makeText(getContext(), "Credenciales invalidas.", Toast.LENGTH_LONG).show();
        }
    }*/

}