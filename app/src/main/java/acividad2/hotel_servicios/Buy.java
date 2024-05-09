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
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Buy#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Buy extends Fragment implements View.OnClickListener {

    private Button btn_tablet;
    private ImageView button_back;

    private TextView  txt_discount,txt_iva,txt_totalsum,txt_subtotal,txt_fit,txt_precio_3_buy,txt_precio_4_buy,txt_precio_6_buy,txt_precio_7_buy,txt_precio_8_buy,txt_precio_9_buy,txt_precio_10_buy;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int suma_total;

    private String nombre_user ;


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
        button_back= (ImageView) getActivity().findViewById(R.id.button_back);
        // ALL BUTTON this for implements
        button_back.setOnClickListener(this);
        btn_tablet.setOnClickListener(this);
        // ALL TEXTVIEW

        txt_precio_3_buy = (TextView) getActivity().findViewById(R.id.txt_precio_3_buy);
        txt_precio_4_buy= (TextView) getActivity().findViewById(R.id.txt_precio_4_buy);
        txt_precio_6_buy= (TextView) getActivity().findViewById(R.id.txt_precio_6_buy);
        txt_precio_7_buy= (TextView) getActivity().findViewById(R.id.txt_precio_7_buy);
        txt_precio_8_buy= (TextView) getActivity().findViewById(R.id.txt_precio_8_buy);
        txt_precio_9_buy= (TextView) getActivity().findViewById(R.id.txt_precio_9_buy);
        txt_precio_10_buy= (TextView) getActivity().findViewById(R.id.txt_precio_10_buy);
        txt_fit = (TextView)  getActivity().findViewById(R.id.txt_fit);

        txt_discount = (TextView) getActivity().findViewById(R.id.txt_precio_10_buy);
        txt_iva= (TextView) getActivity().findViewById(R.id.txt_iva);
        txt_totalsum= (TextView) getActivity().findViewById(R.id.txt_totalsum);
        txt_subtotal= (TextView) getActivity().findViewById(R.id.txt_subtotal);
        // INSERTS DE TODOS LOS BUNBLE DE CART

        txt_fit.setText( getArguments().getString("name_acc"));
        txt_precio_3_buy.setText("$ " +getArguments().getString( "pice7"));
        txt_precio_4_buy.setText("$ " +getArguments().getString("pice2"));
        txt_precio_6_buy.setText("$ " +getArguments().getString("pice5"));
        txt_precio_7_buy.setText("$ " +getArguments().getString("pice3"));
        txt_precio_8_buy.setText("$ " +getArguments().getString("pice4"));
        txt_precio_9_buy.setText("$ " +getArguments().getString("pice6"));
        txt_precio_10_buy.setText("$ " +getArguments().getString("pice1"));

        nombre_user = getArguments().getString("name_acc");
        bundle.putString("name" , getArguments().getString("name_acc"));


        // Obtiene el texto de los TextViews y los convierte a números
        String precioTexto1 = txt_precio_3_buy.getText().toString();
        String precioTexto2 = txt_precio_4_buy.getText().toString();

        String precioTexto3 = txt_precio_6_buy.getText().toString();
        String precioTexto4 = txt_precio_7_buy.getText().toString();
        String precioTexto5 = txt_precio_8_buy.getText().toString();
        String precioTexto6 = txt_precio_9_buy.getText().toString();
        String precioTexto7 = txt_precio_10_buy.getText().toString();



        if (!precioTexto1.isEmpty() && !precioTexto2.isEmpty() && !precioTexto3.isEmpty() &&
                !precioTexto4.isEmpty() && !precioTexto5.isEmpty() && !precioTexto6.isEmpty() && !precioTexto7.isEmpty())  {
            double precio1 = Double.parseDouble(precioTexto1.replace("$ ", ""));
            double precio2 = Double.parseDouble(precioTexto2.replace("$ ", ""));
            double precio3 = Double.parseDouble(precioTexto3.replace("$ ", ""));
            double precio4 = Double.parseDouble(precioTexto4.replace("$ ", ""));
            double precio5 = Double.parseDouble(precioTexto5.replace("$ ", ""));
            double precio6 = Double.parseDouble(precioTexto6.replace("$ ", ""));
            double precio7 = Double.parseDouble(precioTexto7.replace("$ ", ""));


            // Suma los precios utilizando la clase CalculadoraPrecio
            double total = CalculadoraPrecio.sumarPrecios(precio1, precio2 , precio3, precio4, precio5, precio6, precio7);

            txt_subtotal.setText("$ " + total);
        } else {
            // Manejar caso donde al menos uno de los precios está vacío
            // Por ejemplo, mostrar un mensaje de error o tomar alguna acción adecuada
        }

    }

    public static class CalculadoraPrecio {
        public static double sumarPrecios(double precio1, double precio2 , double precio3 , double precio4 , double precio5 , double precio6, double precio7 ) {
                double suma  = precio1 + precio2 + precio3 + precio4 + precio5 + precio6 + precio7;

            return suma;
        }
    }



    @Override
    public void onClick(View view) {
        if (view.getId() == btn_tablet.getId()){
            Navigation.findNavController(view).navigate(R.id.login,bundle);
        } else if (view.getId() == button_back.getId()) {

            Navigation.findNavController(view).navigate(R.id.cart,bundle);
        }
    }


}