package com.example.edulearn;

import io.github.jan.supabase.SupabaseClient;
import io.github.jan.supabase.gotrue.GoTrue;
import io.github.jan.supabase.gotrue.GoTrueKt;
import io.github.jan.supabase.postgrest.Postgrest;
import io.github.jan.supabase.postgrest.PostgrestKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class SupabaseHelper {

    /*private static SupabaseClient supabase;

    public static SupabaseClient getClient() {
        if (supabase == null) {
            // Create client using the Kotlin function exposed via SupabaseClientKt
            supabase = SupabaseClientKt.createSupabaseClient(
                    "https://zmcefbdslaxmncpikxpq.supabase.co",
                    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InptY2VmYmRzbGF4bW5jcGlreHBxIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDY1MzA2ODQsImV4cCI6MjA2MjEwNjY4NH0.QZCPmhnw4DM2X9f-iLwV2-eEIkSdAkOVinA0DXgTNCc"
            );

            // Install GoTrue module
            GoTrueKt.installGoTrue(supabase, (Function1<GoTrue.Config, Unit>) config -> {
                // Configure GoTrue if needed (optional)
                return Unit.INSTANCE;
            });

            // Install Postgrest module
            PostgrestKt.installPostgrest(supabase, (Function1<Postgrest.Config, Unit>) config -> {
                // Configure Postgrest if needed (optional)
                return Unit.INSTANCE;
            });
        }

        return supabase;
    }*/
}
