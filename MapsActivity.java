package com.a.testdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback , LocationListener {

    private GoogleMap mMap;

    // creating a variable
    // for search view.
    SearchView searchView;
    LinearLayout lin_2;
    LinearLayout lin_place;
    String zipcode=null;
    AutocompleteSupportFragment autocompleteSupportFragment;

    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // initializing our search view.
        searchView = findViewById(R.id.idSearchView);

        // Obtain the SupportMapFragment and get notified
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment)this.getSupportFragmentManager().findFragmentById(R.id.map);

        // adding on query listener for our search view.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // on below line we are getting the
                // location name from search view.
                String location = searchView.getQuery().toString();
                // below line is to create a list of address
                // where we will store the list of all address.
                List<Address> addressList = null;
                // checking if the entered location is null or not.
                if (location != null || location.equals("")) {
                    // on below line we are creating and initializing a geo coder.
                    Geocoder geocoder = new Geocoder(MapsActivity.this);
                    try {
                        // on below line we are getting location from the
                        // location name and adding that location to address list.
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // on below line we are getting the location
                    // from our list a first position.
                    Address address = addressList.get(0);

                    // on below line we are creating a variable for our location
                    // where we will add our locations latitude and longitude.
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                    // on below line we are adding marker to that position.
                    mMap.addMarker(new MarkerOptions().position(latLng).title(location));

                    // below line is to animate camera to that position.
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME,Place.Field.LAT_LNG);
                // Start the autocomplete intent.
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields).build(getApplicationContext());
                startActivityForResult(intent, 1);

            }
        });

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        lin_place=findViewById(R.id.lin_place);



        Places.initialize(getApplicationContext(),"AIzaSyBiOb_HpOexBOm8a1p3ckNsc4tqFGt7GbU");

        PlacesClient placesClient =  Places.createClient(getApplicationContext());


        autocompleteSupportFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteSupportFragment.setTypeFilter(TypeFilter.ADDRESS);

        autocompleteSupportFragment.setCountries("IN");

        autocompleteSupportFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME,Place.Field.LAT_LNG));

        autocompleteSupportFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                //  Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());

                LatLng destinationLatLng = place.getLatLng();
                Geocoder geocoder2;
                List<Address> addresses;
                geocoder2 = new Geocoder(getApplicationContext(), Locale.getDefault());

                try {

                    double destlat = destinationLatLng.latitude;
                    double destLon = destinationLatLng.longitude;

                    // Source address
                    addresses = geocoder2.getFromLocation(destlat,destLon, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                    String location = place.getName();
                    // below line is to create a list of address
                    // where we will store the list of all address.
                    List<Address> addressList = null;
                    // checking if the entered location is null or not.
                    if (location != null || location.equals("")) {
                        // on below line we are creating and initializing a geo coder.
                        Geocoder geocoder1 = new Geocoder(MapsActivity.this);
                        try {
                            // on below line we are getting location from the
                            // location name and adding that location to address list.
                            addressList = geocoder1.getFromLocationName(location, 1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        // on below line we are getting the location
                        // from our list a first position.
                        Address address = addressList.get(0);

                        // on below line we are creating a variable for our location
                        // where we will add our locations latitude and longitude.
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                        // on below line we are adding marker to that position.
                        mMap.addMarker(new MarkerOptions().position(latLng).title(location));

                        // below line is to animate camera to that position.
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                    }

                    Toast.makeText(MapsActivity.this, ""+addresses.get(0).getPostalCode(), Toast.LENGTH_SHORT).show();

                    lin_place.setVisibility(View.GONE);

                }catch (Exception e)
                {
                    e.printStackTrace();
                    Constants.dismiss_progress_dialog();
                }

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                //   Log.i(TAG, "An error occurred: " + status);

                // Toast.makeText(TestClass.this, ""+status, Toast.LENGTH_SHORT).show();

            }
        });





        // at last we calling our map fragment to update.
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                LatLng destinationLatLng = place.getLatLng();
                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                try {

                    double destlat = destinationLatLng.latitude;
                    double destLon = destinationLatLng.longitude;

                    getPostalCodeByCoordinates(MapsActivity.this,destlat,destLon);

                    // Source address
                    addresses = geocoder.getFromLocation(destlat,destLon, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                    //autoCompleteTextView1.setText(place.getName());
                   /* Constant.CITY = addresses.get(0).getSubAdminArea();
                    sfStore.setString(Constant.CITY,   Constant.CITY);
                    _City = Constant.CITY;*/
                    String location = place.getName();
                    // below line is to create a list of address
                    // where we will store the list of all address.
                    List<Address> addressList = null;
                    // checking if the entered location is null or not.
                    if (location != null || location.equals("")) {
                        // on below line we are creating and initializing a geo coder.
                        Geocoder geocoder1 = new Geocoder(MapsActivity.this);
                        try {
                            // on below line we are getting location from the
                            // location name and adding that location to address list.
                            addressList = geocoder1.getFromLocationName(location, 1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        // on below line we are getting the location
                        // from our list a first position.
                        Address address = addressList.get(0);

                        // on below line we are creating a variable for our location
                        // where we will add our locations latitude and longitude.
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                        // on below line we are adding marker to that position.
                        mMap.addMarker(new MarkerOptions().position(latLng).title(location));

                        // below line is to animate camera to that position.
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                    }

                    Constants.show_progress_dialog(getApplicationContext());

                    Thread.sleep(1000);
//                    Intent intent = new Intent(getApplicationContext(), RestaurantMainActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);

                    Constants.dismiss_progress_dialog();

                    //   Toast.makeText(EnterLocationScreen.this, ""+addresses.get(0).getPostalCode(), Toast.LENGTH_SHORT).show();

                }catch (Exception e)
                {
                    e.printStackTrace();
                    Constants.dismiss_progress_dialog();
                }

                Log.i("a" ,"Place: " + place.getName() + ", " + place.getId());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                // Log.i("aa", status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    public  String getPostalCodeByCoordinates(Context context, double lat, double lon) throws IOException {

        Geocoder mGeocoder = new Geocoder(context, Locale.getDefault());

        Address address=null;

        if (mGeocoder != null) {

            List<Address> addresses = mGeocoder.getFromLocation(lat, lon, 5);

            if (addresses != null && addresses.size() > 0) {

                for (int i = 0; i < addresses.size(); i++) {
                    address = addresses.get(i);
                    if (address.getPostalCode() != null) {
                        zipcode = address.getPostalCode();
                        // Log.d(TAG, "Postal code: " + address.getPostalCode());
                        break;
                    }

                }
                return zipcode;
            }
        }

        return null;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Log.d("lat", String.valueOf(location.getLatitude()));
        Log.d("long", String.valueOf(location.getLongitude()));

//
    }
}