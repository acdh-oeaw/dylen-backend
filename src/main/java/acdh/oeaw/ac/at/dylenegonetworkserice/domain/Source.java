package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import com.google.common.graph.Network;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PUBLIC)
public class Source {
    @NonNull private String id;
    @NonNull private String name;
    @NonNull private List<Network> networks;
}

