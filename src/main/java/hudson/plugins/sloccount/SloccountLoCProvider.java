package hudson.plugins.sloccount;

import hudson.Extension;
import hudson.model.AbstractBuild;
import org.jenkinsci.plugins.codehealth.LinesOfCode;
import org.jenkinsci.plugins.codehealth.LinesOfCodeProvider;

/**
 * @author Michael Prankl
 */
@Extension
public class SloccountLoCProvider extends LinesOfCodeProvider {
    
    @Override
    public LinesOfCode getLOC(AbstractBuild<?, ?> build) {
        SloccountBuildAction action = build.getAction(SloccountBuildAction.class);
        if (action != null) {
            int fileCount = action.getResult().getStatistics().getFileCount();
            int lineCount = action.getResult().getStatistics().getLineCount();
            LinesOfCode loc = new LinesOfCode(lineCount, fileCount);
            return loc;
        }
        return null;
    }
}
