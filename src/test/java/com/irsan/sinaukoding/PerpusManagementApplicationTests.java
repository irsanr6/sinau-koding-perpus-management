package com.irsan.sinaukoding;

import com.irsan.sinaukoding.entity.Peminjaman;
import com.irsan.sinaukoding.repository.BukuRepository;
import com.irsan.sinaukoding.repository.PeminjamanRepository;
import com.irsan.sinaukoding.util.Constant;
import com.irsan.sinaukoding.util.Helper;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class PerpusManagementApplicationTests {

    @Autowired
    private PeminjamanRepository peminjamanRepository;

    @Autowired
    private BukuRepository bukuRepository;

    @Test
    void contextLoads() {
        Date currenDate = Helper.currentDate();
        Date afterDAte = DateUtils.addDays(Helper.currentDate(), 30);
        System.out.println("Current date " + currenDate);
        System.out.println("After 7 days " + afterDAte);
    }

    @Test
    void countAnggotaId() {
        Long anggotaId = 2L;
        long getCount = peminjamanRepository.countAnggotaId(anggotaId, Constant.STATUS_SELESAI);
        System.out.println("Jumlah " + getCount);
    }

    @Test
    void countBukuId() {
        Long anggotaId = 2L;
        Long bukuId = 2L;
        long getCount = peminjamanRepository.countBukuId(anggotaId, bukuId, Constant.STATUS_SELESAI);
        System.out.println("Jumlah " + getCount);
    }

    @Test
    void getDenda() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date currenDate = Helper.currentDate();
        Date afterDAte = DateUtils.addDays(Helper.currentDate(), 9);
        Date customDate = sdf.parse("2022-10-08 15:18:23");

        String requiredDate = df.format(afterDAte);
        String message = "";
        double denda = 1500;
        long diff = (afterDAte.getTime() - currenDate.getTime()) / (24 * 60 * 60 * 1000);

        if (diff >= 8) {
            message = "Anda dikenakan denda";
            denda = denda * (diff - Constant.LONG_DAYS);
        } else {
            message = "Anda tepat waktu";
            denda = 0;
        }

        System.out.println(message + " " + denda);
        System.out.println("Interval " + diff);
        System.out.println("After date " + requiredDate + ", current date " + currenDate);
    }

    @Test
    void testRepoCustom() {
        Long bukuId = 1L;
        Long jumlahBuku = bukuRepository.getJumlahBuku(bukuId);
        if (jumlahBuku <= 0) {
            System.out.println("buku habis");
        } else {
            System.out.println(jumlahBuku);
        }
    }

}
