package cl.puc.dcc.iic2113.persistencia;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.DropboxAPI.DropboxFileInfo;
import com.dropbox.client2.DropboxAPI.Entry;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.RequestTokenPair;
import com.dropbox.client2.session.Session.AccessType;
import com.dropbox.client2.session.WebAuthSession;

class Remoto implements IPersistencia {

	///////////////////////////////////////////////////////////////////////////
	//                      Your app-specific settings.                      //
	///////////////////////////////////////////////////////////////////////////

	// Replace this with your app key and secret assigned by Dropbox.
	// Note that this is a really insecure way to do this, and you shouldn't
	// ship code which contains your key & secret in such an obvious way.
	// Obfuscation is good.
	final private String APP_KEY = "k5pz39xafe83jj9";
	final private String APP_SECRET = "s1txmqonq96mbra";

	// If you'd like to change the access type to the full Dropbox instead of
	// an app folder, change this value.
	final private AccessType ACCESS_TYPE = AccessType.APP_FOLDER;

	///////////////////////////////////////////////////////////////////////////
	//                      End app-specific settings.                       //
	///////////////////////////////////////////////////////////////////////////
	AppKeyPair appKeys;
	WebAuthSession session;
	private DropboxAPI<WebAuthSession> mDBApi;
	File tokensFile;

	// Get ready for user input
	Scanner input = new Scanner(System.in);
	public Remoto()
	{
		appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
		session = new WebAuthSession(appKeys, ACCESS_TYPE);
		mDBApi = new DropboxAPI<WebAuthSession>(session);
		tokensFile = new File("TOKENS");

		if(!tokensFile.exists())
			InitializeDropbox();
		GetCredentials();

	}
	private void GetCredentials()
	{
		try
		{
			Scanner tokenScanner = new Scanner(tokensFile);       // Initiate Scanner to read tokens from TOKEN file
			String ACCESS_TOKEN_KEY = tokenScanner.nextLine();    // Read key
			String ACCESS_TOKEN_SECRET = tokenScanner.nextLine(); // Read secret
			tokenScanner.close(); //Close Scanner 

			//Re-auth
			AccessTokenPair reAuthTokens = new AccessTokenPair(ACCESS_TOKEN_KEY, ACCESS_TOKEN_SECRET);
			mDBApi.getSession().setAccessTokenPair(reAuthTokens);
			System.out.println("Re-authentication Sucessful!");

			//Run test command
			System.out.println("Hello there, " + mDBApi.accountInfo().displayName);
		}
		catch(Exception e){

		}
	}
	private void InitializeDropbox()
	{
		try
		{
			// Present user with URL to allow app access to Dropbox account on
			System.out.println("Please go to this URL and hit \"Allow\": " + mDBApi.getSession().getAuthInfo().url);
			AccessTokenPair tokenPair = mDBApi.getSession().getAccessTokenPair();

			// Wait for user to Allow app in browser
			System.out.println("Finished allowing?  Enter 'next' if so: ");
			if(input.next().equals("next")){
				RequestTokenPair tokens = new RequestTokenPair(tokenPair.key, tokenPair.secret);
				mDBApi.getSession().retrieveWebAccessToken(tokens);
				PrintWriter tokenWriter = new PrintWriter(tokensFile);
				tokenWriter.println(session.getAccessTokenPair().key);
				tokenWriter.println(session.getAccessTokenPair().secret);
				tokenWriter.close();
				System.out.println("Authentication Successful!");
			}
		}
		catch(Exception e){

		}
	}
	@Override
	public boolean Guardar(Token token) {
		try
		{
			FileOutputStream fileOut =
				new FileOutputStream(token.getDigest().toString()+".per1");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(token);
			out.close();
			
			
			InputStream fileIn = new FileInputStream(token.getDigest().toString()+".per1");
			File f = new File(token.getDigest().toString()+".per1");
			mDBApi.putFile(token.getDigest().toString()+".per",fileIn,f.length(), null, null);
			
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Guardar");
			return false;
		}
	}

	@Override
	public Token RecuperarPorDigest(byte[] digest) {
		// Get file.
		FileOutputStream outputStream = null;
		try {
			File file = new File(digest.toString()+".pers");
			outputStream = new FileOutputStream(file);
			Token t;
			mDBApi.getFile(digest.toString()+".per", null, outputStream, null);
			outputStream.close();
			
			
			FileInputStream fi = new FileInputStream(file);
			
			ObjectInputStream in = new ObjectInputStream(fi);
						
            t = (Token) in.readObject();
            fi.close();
            in.close();
            return t;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public TipoPersistencia MiTipo() {
		return TipoPersistencia.REMOTO;
	}

}
