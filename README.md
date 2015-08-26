# kitchen-material
Android project template that uses ButterKnife, Retrofit, ActiveAndroid, and Gson

<br/>

Change the values of the following located in `colors.xml` according to your color scheme
```
<color name="primary">#2196F3</color>           <!-- color of toolbar -->
<color name="primary_dark">#1976D2</color>      <!-- color of status bar -->
<color name="accent">#009688</color>            <!-- tint of views -->
```

<br/>

##BaseActivity

Extend your activity to `BaseActivity`<br/>
`BaseActivity` extends the `AppCompatActivity`. This automatically applies 
the accent color you set in `colors.xml` as tint to your views.

    public abstract class BaseActivity extends AppCompatActivity {
    
        protected Context mContext;
    
        protected Toolbar mToolbar;
    
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
    
            setContentView(getLayoutResourceId());
            ButterKnife.bind(this);
    
            mContext = this;
    
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            if (mToolbar != null) {
                setSupportActionBar(mToolbar);
            }
    
        }
    
        protected abstract int getLayoutResourceId();
    
    }
    

Override `getLayoutResourceId()` to return the layout that will be used by your Activity.<br/>
For example,

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_example;
    }


If your using ButterKnife, it is already injected in `BaseActivity`.<br/>
Get the Context of your Activity through `BaseActivity`'s variable `mContext`.<br/>
<br/>

To include a toolbar in your layout

`<include layout="@layout/toolbar"></include>`

this toolbar will be set as your support ActionBar.

See `ExampleActivity` as example of an activity that extends `BaseActivity`.

<br/>

##Retrofit, ActiveAndroid, and Gson
**Retrofit** turns your REST API into a Java interface.<br/>
**ActiveAndroid** is an active record style ORM.<br/>
**Gson** is a Java library that can be used to convert Java Objects into their JSON representation.<br/>

Create your model inside package `models`.<br/>
Extend the `Model` class of ActiveAndroid. <br/>
Use the following annotations to the field of your model:<br/>
```
@Expose - expose the field to Gson
@SerializedName("") - name used by Gson
@Column(name = "") - column name used by ActiveAndroid
```

See `Contributor.java` model as example
