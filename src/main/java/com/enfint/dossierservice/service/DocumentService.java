package com.enfint.dossierservice.service;

import com.enfint.dossierservice.entity.Application;

import java.io.File;
import java.util.List;

public interface DocumentService {
    List<File> createDocuments(Application application);
}
