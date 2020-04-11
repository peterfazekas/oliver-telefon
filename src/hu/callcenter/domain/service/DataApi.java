package hu.callcenter.domain.service;

import hu.callcenter.domain.model.CallLog;

import java.util.List;

public class DataApi {

    private final FileReader fileReader;
    private final DataParser dataParser;

    public DataApi(FileReader fileReader, DataParser dataParser) {
        this.fileReader = fileReader;
        this.dataParser = dataParser;
    }

    public List<CallLog> getData(String input) {
        return dataParser.parse(fileReader.read(input));
    }
}
