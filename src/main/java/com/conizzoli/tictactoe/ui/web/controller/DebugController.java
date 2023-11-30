package com.conizzoli.tictactoe.ui.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DebugController {
  @GetMapping(name = "debug", path = "/debug")
  public void debug() {}
}
