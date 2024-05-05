package acividad2.hotel_servicios;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Buy#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Buy extends Fragment implements View.OnClickListener {

    private Button btn_tablet;
    private ImageButton Button_back;

    private TextView txt_total, txt_precio_one_buy,txt_precio_2_buy,txt_precio_3_buy,txt_precio_4_buy,txt_precio_5_buy,txt_precio_6_buy,txt_precio_7_buy,txt_precio_8_buy,txt_precio_9_buy,txt_precio_10_buy;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int suma_total;

    public Buy() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Buy.
     */
    // TODO: Rename and change types and number of parameters
    public static Buy newInstance(String param1, String param2) {
        Buy fragment = new Buy();
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
    Bundle bundle = new Bundle();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.buy, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

            // ALL BUTTON
        btn_tablet  = (Button) getActivity().findViewById(R.id.btn_add);
        Button_back= (ImageButton) getActivity().findViewById(R.id.Button_back);
        // ALL BUTTON this for implements
        Button_back.setOnClickListener(this);
        btn_tablet.setOnClickListener(this);
        // ALL TEXTVIEW
        txt_total = (TextView) getActivity().findViewById(R.id.txt_total);
        txt_precio_3_buy = (TextView) getActivity().findViewById(R.id.txt_precio_3_buy);
        txt_precio_one_buy= (TextView) getActivity().findViewById(R.id.txt_precio_one_buy);
        txt_precio_2_buy= (TextView) getActivity().findViewById(R.id.txt_precio_2_buy);
        txt_precio_4_buy= (TextView) getActivity().findViewById(R.id.txt_precio_4_buy);
        txt_precio_5_buy= (TextView) getActivity().findViewById(R.id.txt_precio_5_buy);
        txt_precio_6_buy= (TextView) getActivity().findViewById(R.id.txt_precio_6_buy);
        txt_precio_7_buy= (TextView) getActivity().findViewById(R.id.txt_precio_7_buy);
        txt_precio_8_buy= (TextView) getActivity().findViewById(R.id.txt_precio_8_buy);
        txt_precio_9_buy= (TextView) getActivity().findViewById(R.id.txt_precio_9_buy);
        txt_precio_10_buy= (TextView) getActivity().findViewById(R.id.txt_precio_10_buy);

        // INSERTS DE TODOS LOS BUNBLE DE CART

        txt_precio_one_buy.setText(getArguments().getString(""));
        txt_precio_2_buy.setText(getArguments().getString(""));
        txt_precio_3_buy.setText(getArguments().getString("pice7"));
        txt_precio_4_buy.setText(getArguments().getString("pice2"));
        txt_precio_5_buy.setText(getArguments().getString(""));
        txt_precio_6_buy.setText(getArguments().getString("pice5"));
        txt_precio_7_buy.setText(getArguments().getString("pice3"));
        txt_precio_8_buy.setText(getArguments().getString("pice4"));
        txt_precio_9_buy.setText(getArguments().getString("pice6"));
        txt_precio_10_buy.setText(getArguments().getString("pice1"));

        int precio_1 = Integer.parseInt(txt_precio_3_buy.getText().toString());


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == btn_tablet.getId()){
            Navigation.findNavController(view).navigate(R.id.login);
        } else if (view.getId() == Button_back.getId()) {
            Navigation.findNavController(view).navigate(R.id.cart,bundle);
        }
    }


}