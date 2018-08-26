
void main()
{
	float f1, f2;
	double d1, d2;
	
	// Check out the floats
	f1 = 0.2f;                  // OK
	f1 = 10.123456f;            // OK
	f1 = 12.3456789f;           // More than max number of significant digits (6)
	f1 = 3.402823466E+38f;     // Max limit?
	f1 = 4.402823466E+38F;     // Exceeded max limit?
	f1 = 3.402823466E+39f;     // Exceeded max limit?
	f1 = 1.175494351E-38f;     // OK
	f1 = 1.175494351E-39f;     // Below min limit?
	f1 = 0.175494351E-38F;     // Below min limit?

	// Float max values
	f1 = 12345678901234567890123456789012345678.0f; 
	f1 = 123456789012345678901234567890123456789.0f; 
	f1 = 223456789012345678901234567890123456789.0f; 
	f1 = 323456789012345678901234567890123456789.0f; 
	f1 = 340282346600000000000000000000000000000.0f;  //Threshold for max
	f1 = 340282346700000000000000000000000000000.0f;
	f1 = 423456789012345678901234567890123456789.0f;
	f1 = 1234567890123456789012345678901234567890.0f;

	// Float min values
	f1 = -1.175494351E-37f;     // OK
	f1 = -123456789012345678901234567890123456789.0f; 
	f1 = -1234567890123456789012345678901234567890.0f; 
	

	// Check the doubles
	d1 = 0.2;                           // OK
	d1 = 10.123456789012345;            // OK
	d1 = 12.12345678901234567;              // More than max number of significant digits (15)
	d1 = 1.7976931348623158E+308;              // Max limit?
	d1 = 2.7976931348623158E+308;             // Exceeded max limit?
	d1 = 1.7976931348623158E+309;      // Exceeded max limit?
	d1 = 2.2250738585072014E-308;     // OK
	d1 = 0.2250738585072014E-309;    // Below min limit?
	d1 = 2.2250738585072014E-309;     // Below min limit?
	d1 = 2.2250738585072014E-3010;     // Below min limit?

}