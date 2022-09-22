package uz.pdp.appjparelationships.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UniversityDto { // MA'LUMOTLARNI TASHISH UCHUN XIZMAT QILADI

    private String name;
    private String city;
    private String district;
    private String street;
}
