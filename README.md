# Veterinary Management System REST API

Bu proje, bir veteriner kliniği veya merkezi için geliştirilmiş olan örnek bir yönetim sistemidir. Spring Boot ve katmanlı mimari kullanılarak oluşturulmuştur.

## Genel Bakış

Veterinary Management System, veterinerlerin ve personelin hayvan sahipleri, randevular, aşı kayıtları, doktorlar ve hayvanlar üzerinde yönetim işlemlerini gerçekleştirebildiği bir platformdur. Proje, aşağıdaki temel işlevleri içermektedir:

- Hayvan sahibi kaydı
- Hayvan kaydı
- Doktor kaydı
- Doktor müsait günü kaydı
- Randevu kaydı
- Hayvana ait aşı kaydı
- Raporlama İşlemi
- Filtreleme işlemleri (Hayvanlar, Hayvan Sahipleri, Randevular vs.)
- Aşı koruyuculuk kontrolü
- Exception kullanımı ve HTTP durum kodları

## Proje Yapısı

Proje, aşağıdaki katmanlı mimari yapısını takip etmektedir:

- **api:** Controller sınıflarının bulunduğu katman.
- **business:** Service sınıflarının yer aldığı iş mantığı katmanı.
- **core:** Temel işlevlerin ve ortak özelliklerin bulunduğu katman.
- **dao:** Veritabanı işlemlerini gerçekleştiren Repository sınıflarının bulunduğu katman.
- **dto:** Veri transfer nesneleri (DTO) katmanı.

## Uygulama Kurulumu ve Çalıştırılması

Projenin çalıştırılması için aşağıdaki adımları izleyebilirsiniz:

1. Projenin klonlanması: git clone <proje-repository-linki.git> 
2. PostgreSQL veritabanı bağlantısının yapılandırılması:
- `application.properties` dosyasında gerekli veritabanı bağlantı bilgilerini güncelleyin.
3. Projenin derlenmesi ve çalıştırılması: mvn spring-boot:run

## UML Diyagramı
![UML Diagram](https://github.com/salginerdi/Patika-Bootcamp-Hafta-13-VetManagementSystem/assets/110611268/b364be27-cd1e-4916-a08b-cdaef00e8861)

## API Endpointleri

![endpoints-1](https://github.com/salginerdi/Patika-Bootcamp-Hafta-13-VetManagementSystem/assets/110611268/1f06722b-538d-4ead-af13-5162e08602cc)
![endpoints-2](https://github.com/salginerdi/Patika-Bootcamp-Hafta-13-VetManagementSystem/assets/110611268/3d5edeb1-21e6-404d-be85-980d2fcf98b9)
![endpoints-3](https://github.com/salginerdi/Patika-Bootcamp-Hafta-13-VetManagementSystem/assets/110611268/48ad5d28-960f-4940-b35f-26f046d50a6b)
![endpoints-4](https://github.com/salginerdi/Patika-Bootcamp-Hafta-13-VetManagementSystem/assets/110611268/79fc8a50-c705-44eb-b8c0-6384d1dfaceb)

-------------------------------------------------------------------------------------------------------------------------------------------------

# Veterinary Management System REST API

This project is an example management system developed for a veterinary clinic or center. It has been created using Spring Boot and a layered architecture.

## Overview

The Veterinary Management System is a platform where veterinarians and staff can manage animal owners, appointments, vaccination records, doctors, and animals. The project includes the following key functionalities:

- Animal owner registration
- Animal registration
- Doctor registration
- Doctor availability scheduling
- Appointment scheduling
- Vaccination records for animals
- Reporting process
- Filtering operations (Animals, Animal Owners, Appointments, etc.)
- Vaccination protection check
- Usage of exceptions and HTTP status codes

## Project Structure

The project follows the following layered architectural structure:

- **api:** Layer containing Controller classes.
- **business:** Business logic layer containing Service classes.
- **core:** Layer containing core functionalities and common features.
- **dao:** Layer containing Repository classes performing database operations.
- **dto:** Data Transfer Object (DTO) layer.

## Installation and Running the Application

To run the project, you can follow these steps:

1. Clone the project: `git clone <project-repository-link.git>`
2. Configure the PostgreSQL database connection:
   - Update the necessary database connection information in the `application.properties` file.
3. Compile and run the project: `mvn spring-boot:run`

## UML Diagram
![UML Diagram](https://github.com/salginerdi/Patika-Bootcamp-Hafta-13-VetManagementSystem/assets/110611268/b364be27-cd1e-4916-a08b-cdaef00e8861)

## API Endpoints

![endpoints-1](https://github.com/salginerdi/Patika-Bootcamp-Hafta-13-VetManagementSystem/assets/110611268/1f06722b-538d-4ead-af13-5162e08602cc)
![endpoints-2](https://github.com/salginerdi/Patika-Bootcamp-Hafta-13-VetManagementSystem/assets/110611268/3d5edeb1-21e6-404d-be85-980d2fcf98b9)
![endpoints-3](https://github.com/salginerdi/Patika-Bootcamp-Hafta-13-VetManagementSystem/assets/110611268/48ad5d28-960f-4940-b35f-26f046d50a6b)
![endpoints-4](https://github.com/salginerdi/Patika-Bootcamp-Hafta-13-VetManagementSystem/assets/110611268/79fc8a50-c705-44eb-b8c0-6384d1dfaceb)

