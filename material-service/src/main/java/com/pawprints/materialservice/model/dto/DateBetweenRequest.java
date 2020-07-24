package com.pawprints.materialservice.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class DateBetweenRequest {

   private LocalDate start;
   private LocalDate end;

   public DateBetweenRequest(LocalDate start, LocalDate end) {
      this.start = start;
      this.end = end;
   }
}
