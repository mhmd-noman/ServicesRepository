//package com.services.test;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.net.URISyntaxException;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Stream;
//
//import com.google.auth.oauth2.GoogleCredentials;
//import com.itextpdf.text.BadElementException;
//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Chunk;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.FontFactory;
//import com.itextpdf.text.Image;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.Phrase;
//import com.itextpdf.text.Rectangle;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.services.configurator.ServicesConfigurator;
//
//import common.beans.Invoice;
//import common.beans.Order;
//import common.beans.Product;
//import common.beans.User;
//import common.exception.handling.BaseException;
//import common.request.MainRequestObject;
//import common.response.MainResponseObject;  
//@Service
//public class FirebaseMessagingService {
//
//    private final FirebaseMessaging firebaseMessaging;
//
//    public FirebaseMessagingService(FirebaseMessaging firebaseMessaging) {
//        this.firebaseMessaging = firebaseMessaging;
//    }
//
//
//    public String sendNotification(Note note, String token) throws FirebaseMessagingException {
//
//        Notification notification = Notification
//                .builder()
//                .setTitle(note.getSubject())
//                .setBody(note.getContent())
//                .build();
//
//        Message message = Message
//                .builder()
//                .setToken(token)
//                .setNotification(notification)
//                .putAllData(note.getData())
//                .build();
//
//        return firebaseMessaging.send(message);
//    }
//
//}