package com.example.transferapi.data;

import com.example.transferapi.domain.TransferItem;
import com.example.transferapi.domain.TransferType;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TransferItemsData
{
    public static Collection<TransferItem> GetData()
    {
        return IntStream.range(1, 100).mapToObj(i -> new TransferItem(
                GetRandomIpAddress()+"/1"+i,
                "From Company" + i,
                "To Company " + i + 2,
                LocalDateTime.now(),
                GetTransferType(i)
                )).collect(Collectors.toList());
    }

    public static String GetListOfIps(int i)
    {
        return i % 2 == 0 ? GetRandomIpAddress() + "/2" + i : GetRandomIpAddress() + "/4" + i;
    }

    private static TransferType GetTransferType(int i)
    {
        return i % 2 == 0 ? TransferType.Policy : TransferType.MergerOrAcquisition;
    }

    private static String GetRandomIpAddress()
    {
        Random random = new Random();
        return String.format("%d.%d.%d.%d",
                random.nextInt(50),
                random.nextInt(100),
                random.nextInt(150),
                random.nextInt(255));
    }
}