// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    // Plugin que lê o google-services.json. Só é declarado aqui, quem o aplica é o módulo app
    alias(libs.plugins.google.services) apply false
}
