import io.github.jan.supabase.SupabaseClient;
import io.github.jan.supabase.createSupabaseClient;
import io.github.jan.supabase.postgrest.Postgrest;

public class SupabaseHelper {
    private static SupabaseClient supabase;

    public static SupabaseClient getClient() {
        if (supabase == null) {
            supabase = createSupabaseClient(
                    "https://zmcefbdslaxmncpikxpq.supabase.co",
                    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InptY2VmYmRzbGF4bW5jcGlreHBxIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDY1MzA2ODQsImV4cCI6MjA2MjEwNjY4NH0.QZCPmhnw4DM2X9f-iLwV2-eEIkSdAkOVinA0DXgTNCc"
            ).getSelf();
        }
        return supabase;
    }
}