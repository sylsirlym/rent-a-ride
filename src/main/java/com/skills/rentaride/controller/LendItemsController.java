package com.skills.rentaride.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skills.rentaride.dtos.requests.CreateItemDTO;
import com.skills.rentaride.dtos.requests.CreateItemTypeDTO;
import com.skills.rentaride.dtos.requests.EditItemTypeDTO;
import com.skills.rentaride.dtos.responses.ResponseDTO;
import com.skills.rentaride.exceptions.*;
import com.skills.rentaride.services.ItemsService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sylvester
 * Project rent-a-ride
 * User: sylvester
 * Date: 2/25/20
 * Time: 1:06 AM
 */
@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class LendItemsController {
 private ItemsService itemsService;
 private final Logger logger;
 private final ObjectMapper objectMapper;

 @PostMapping("/item-types/create")
 public ResponseDTO authenticateCustomerPin(@RequestBody CreateItemTypeDTO createItemTypeDTO) throws JsonProcessingException {
  logger.info("Create item type. Payload::{}", objectMapper.writeValueAsString(createItemTypeDTO));
  return itemsService.createItemType(createItemTypeDTO);
 }

 @GetMapping("/item-types/fetch")
 public ResponseDTO fetchItemType() throws JsonProcessingException {
  logger.info("Fetch item types controller");
  return itemsService.fetchItemType();
 }

 @PutMapping("/item-types/update")
 public ResponseDTO updateItemType(@RequestBody EditItemTypeDTO editItemTypeDTO) throws JsonProcessingException, ItemTypeNotFoundException {
  logger.info("About to update item type with payload::{}", objectMapper.writeValueAsString(editItemTypeDTO));
  return itemsService.updateItemType(editItemTypeDTO);
 }

 @PostMapping("/item/create")
 public ResponseDTO createItem(@RequestBody CreateItemDTO createItemDTO) throws JsonProcessingException, InvalidPinStatusException, GenericException, ItemTypeNotFoundException, ItemOwnerNotFoundException {
  logger.info("Create user controller. Payload::{}", objectMapper.writeValueAsString(createItemDTO));
  return itemsService.createItem(createItemDTO);
 }

 @GetMapping("/items/fetch")
 public ResponseDTO fetchItems() throws JsonProcessingException {
  logger.info("Fetch items controller");
  return itemsService.fetchItems();
 }

 @PostMapping("/item/fetch/{itemID}")
 public ResponseDTO fetchItem(@PathVariable int itemID) throws JsonProcessingException, ItemNotFoundException {
  logger.info("Fetch items controller");
  return itemsService.fetchItem(itemID);
 }
}
