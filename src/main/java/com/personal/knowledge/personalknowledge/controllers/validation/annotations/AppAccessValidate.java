package com.personal.knowledge.personalknowledge.controllers.validation.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Active 'app-access' header token validation in controller request
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AppAccessValidate {
}
