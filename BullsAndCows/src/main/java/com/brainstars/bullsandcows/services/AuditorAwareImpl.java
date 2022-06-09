package com.brainstars.bullsandcows.services;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

/**
 * Implementation of auditor for getting date of creation or modification.
 */

public class AuditorAwareImpl implements AuditorAware<String> {
  @Override
  public Optional<String> getCurrentAuditor() {
    return Optional.of("Current Auditor");
  }
}

