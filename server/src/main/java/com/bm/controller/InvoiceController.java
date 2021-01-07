package com.bm.controller;

import com.bm.common.ScheduleStatus;
import com.bm.exception.ResourceNotFoundException;
import com.bm.model.Schedule;
import com.bm.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.Date;

@RestController
@RequestMapping("/api/v1")
public class InvoiceController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    /**
     * Choose Payment Method Cash, Credit, or Fawry.
     * This API is responsible for payment confirmation and Invoicing.
     *
     * @param appointmentId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/invoice/appointment/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> createInvoice(@PathVariable(value = "id") Long appointmentId) throws IOException, ResourceNotFoundException {
        Schedule schedule = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("No Appointment Found :: " + appointmentId));
        String filename = "invoice.txt";
        File file = new File(filename);
        FileWriter fr = new FileWriter(file, false);
        BufferedWriter br = new BufferedWriter(fr);
        buildInvoice(schedule, fr, br);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition",
                String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/txt")).body(resource);

        return responseEntity;
    }

    private void buildInvoice(Schedule schedule, FileWriter fr, BufferedWriter br) throws IOException {
        br.write("=================================================================");
        br.write("\n");
        br.write("=================================================================");
        br.write("\n");
        br.write("Dear Customer, your Invoice is generated for branch : " + schedule.getBranchId());
        br.write("\n");
        br.write("Date of Invoice : " + new Date().toString());
        br.write("\n");
        br.write("Name of the Customer : " + schedule.getCustomerName());
        br.write("\n");
        br.write("Address : " + schedule.getCustomerAddress());
        br.write("\n");
        br.write("Mobile : " + schedule.getCustomerMobile());
        br.write("\n");
        br.write("Email : " + schedule.getCustomerEmail());
        br.write("\n");
        br.write("Appointment Date : " + schedule.getAppointmentDate());
        br.write("\n");
        br.write("Vaccination Status : " + schedule.getVaccinationStatus());
        br.write("\n");
        br.write("Appointment Status : " + schedule.getStatus());
        br.write("\n");
        br.write("Total amount : " + schedule.getTotalAmount());
        br.write("\n");
        br.write("This is automatic geenerated invoice signature not needed");
        br.write("\n");
        br.write("=================================================================");
        br.write("\n");
        br.write("=================================================================");
        br.close();
        fr.close();
    }
}
