package com.pawprints.edgeservice.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class DateBetweenRequest {

   private LocalDate start;
   private LocalDate end;
}
