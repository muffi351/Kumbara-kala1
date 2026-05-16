IMPLEMENTATION 


Step 1: Project Creation 
The project was first created in Android Studio using the “Empty Activity” template. Kotlin 
was selected as the programming language. Necessary project configurations such as package 
name, SDK version, and application theme were set during this stage. 



Step 2: User Interface Design 
The application user interface was designed using XML layouts. Components such as Buttons, 
ImageView, Spinner, TextView, and EditText were added to the activity layout.   
The interface included: 
• Upload Image button  
• Product image display area  
• Product selection spinner  
• Benefit generation button  
• Result display section  
• Artisan name and phone number fields  
• WhatsApp share button  



Step 3: Image Upload Functionality 
Image upload functionality was implemented using Android Intent and MediaStore. Users can 
select product images from the mobile gallery, and the selected image is displayed inside the 
ImageView component. 



Step 4: Product Benefit Generation 
A Spinner component was used to allow users to select pottery items such as Clay Water Pot, 
Curd Pot, Tea Cup, and Diya Lamp. Based on the selected item, predefined health and eco
friendly benefits were generated using Kotlin conditional statements. 



 
Step 5: Digital Story Card Creation 
Android Canvas and Bitmap functionalities were implemented to generate attractive digital 
story cards. The application combines: 
• Product image  
• Product benefits  
• Artisan details  
• Branding text  
into a single professional promotional card. 





Step 6: WhatsApp Sharing Feature 
The generated story card was converted into an image file and shared using Android Share 
Intent. FileProvider was implemented to securely share image files with WhatsApp and other 
applications. 




 Step 7: Final UI Enhancement 
Traditional clay colors, artistic layouts, decorative borders, and readable text styles were added 
to improve the overall appearance of the application and make it visually attractive for users.
