package ru.progmatik.main.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class DownloadFileInfoJson {
    @Getter
    @Setter
    Integer VersionId;
    @Getter
    @Setter
    String FiasCompleteXmlUrl;

    @Getter
    @Setter
    String FiasDeltaXmlUrl;
}
